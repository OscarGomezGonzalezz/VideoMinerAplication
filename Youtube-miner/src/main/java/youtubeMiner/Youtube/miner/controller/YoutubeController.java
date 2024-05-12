package youtubeMiner.Youtube.miner.controller;

import youtubeMiner.Youtube.miner.etl.ETL;
import youtubeMiner.Youtube.miner.exception.ChannelNotFound;
import youtubeMiner.Youtube.miner.model.VideoMiner.Channel;
import youtubeMiner.Youtube.miner.service.Channel.ChannelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channels")
public class YoutubeController {
    @Autowired
    ChannelService channelService;
    @Autowired
    ETL etl;
    @GetMapping("/{id}")
    public Channel getChannel(@PathVariable String id,@RequestParam(defaultValue = "10") Integer maxVideos,
                              @RequestParam(defaultValue = "10") Integer maxComments) throws ChannelNotFound {
            youtubeMiner.Youtube.miner.model.channel.Channel channel=channelService.getChannel(id);
            return etl.transformChannel(channel,maxVideos,maxComments);
    }
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Channel createChannel(@PathVariable String id,@RequestParam(defaultValue = "10") Integer maxVideos,
                                 @RequestParam(defaultValue = "10") Integer maxComments) throws ChannelNotFound {
        return channelService.createChannel(getChannel(id, maxVideos, maxComments));
    }


}
