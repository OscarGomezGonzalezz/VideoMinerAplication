package vimeoMiner.service.Video;


import vimeoMiner.model.Video.Video;
import vimeoMiner.model.Video.VideoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    private static final Integer defaultPageSize =15;
    @Autowired
    RestTemplate restTemplate;
    public VideoSearch getVideos(String channel, Integer page, Integer pageSize){
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization","Bearer 858610e23a94d8956a8de5bbf8e28e8c");
        HttpEntity<VideoSearch> req=new HttpEntity<>(null,headers);
        HttpEntity<VideoSearch> search=restTemplate.exchange(String.format("https://api.vimeo.com/channels/%s/videos?page=%d&per_page=%d",channel,page,pageSize), HttpMethod.GET,req, VideoSearch.class);
        return search.getBody();
    }
    public List<Video> getNumberVideos(String channel, Integer number){
        int rest=number;
        int page=1;
        boolean next=true;
        List<Video> videos=new ArrayList<>();
        while(rest>0 && next){
            int requestedVideos=Integer.min(rest, defaultPageSize);
            VideoSearch search=getVideos(channel,page,requestedVideos);
            videos.addAll(search.getVideos());
            next=search.getPaging().getNext()!=null;
            page++;
            rest-=requestedVideos;
        }
        return videos;
    }
}
