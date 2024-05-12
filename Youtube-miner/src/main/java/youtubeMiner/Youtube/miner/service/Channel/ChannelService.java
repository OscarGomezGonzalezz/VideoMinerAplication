package youtubeMiner.Youtube.miner.service.Channel;

import youtubeMiner.Youtube.miner.exception.ChannelNotFound;
import youtubeMiner.Youtube.miner.model.channel.Channel;
import youtubeMiner.Youtube.miner.model.channel.ChannelSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChannelService {
    @Autowired
    RestTemplate restTemplate;
    public Channel getChannel(String id) throws ChannelNotFound{
        ChannelSearch search=restTemplate.getForObject(String.format("https://www.googleapis.com/youtube/v3/channels?" +
                "id=%s&key=AIzaSyC1Q7p1wV3YzryZZnFjp7Nr_cypZ2oLwDg&part=snippet",id),ChannelSearch.class);
        List<Channel> channels=search.getItems();
        if(channels==null){
            throw new ChannelNotFound();
        }
        return search.getItems().get(0);
    }
    public youtubeMiner.Youtube.miner.model.VideoMiner.Channel createChannel(youtubeMiner.Youtube.miner.model.VideoMiner.Channel channel){
        return restTemplate.postForObject("http://localhost:8080/videominer/channels",channel, youtubeMiner.Youtube.miner.model.VideoMiner.Channel.class);
    }
}
