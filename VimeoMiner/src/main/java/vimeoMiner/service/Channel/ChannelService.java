package vimeoMiner.service.Channel;


import vimeoMiner.exception.ChannelNotFound;

import vimeoMiner.model.Channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



@Service
public class ChannelService {
    @Autowired
    RestTemplate restTemplate;
    public Channel getChannel(Integer id) throws ChannelNotFound {
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization","Bearer 858610e23a94d8956a8de5bbf8e28e8c");
        HttpEntity<Channel> req=new HttpEntity<>(null,headers);
        try{
            HttpEntity<Channel> channel=restTemplate.exchange(String.format("https://api.vimeo.com/channels/%d",id), HttpMethod.GET,req, Channel.class);
            return channel.getBody();
        }
        catch(HttpClientErrorException.NotFound e){
            throw new ChannelNotFound();
        }
    }
    public vimeoMiner.model.VideoMiner.Channel createChannel(vimeoMiner.model.VideoMiner.Channel channel){
        return restTemplate.postForObject("http://localhost:8080/videominer/channels",channel,vimeoMiner.model.VideoMiner.Channel.class);
    }
}
























