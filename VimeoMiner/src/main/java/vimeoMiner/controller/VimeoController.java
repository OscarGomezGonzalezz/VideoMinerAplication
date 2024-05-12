package vimeoMiner.controller;



import vimeoMiner.exception.ChannelNotFound;

import vimeoMiner.model.Channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vimeoMiner.service.Channel.ChannelService;
import vimeoMiner.etl.ETL;

@RestController
@RequestMapping("/channels")
public class VimeoController {
    @Autowired
    ChannelService channelService;
    @Autowired
    ETL etl;
    @GetMapping("/{id}")
    public vimeoMiner.model.VideoMiner.Channel getChannel(@PathVariable Integer id,
                                               @RequestParam(defaultValue = "10") Integer maxVideos,
                                               @RequestParam(defaultValue = "10") Integer maxComments) throws ChannelNotFound {
        Channel channel=channelService.getChannel(id);
        return etl.transformChannel(channel,maxVideos,maxComments);
    }
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public vimeoMiner.model.VideoMiner.Channel createChannel(@PathVariable Integer id,
                                                              @RequestParam(defaultValue = "10") Integer maxVideos,
                                                              @RequestParam(defaultValue = "10") Integer maxComments) throws ChannelNotFound {
        return channelService.createChannel(getChannel(id, maxVideos,maxComments));
    }
}
