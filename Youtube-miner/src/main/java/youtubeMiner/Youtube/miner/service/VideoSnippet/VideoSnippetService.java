package youtubeMiner.Youtube.miner.service.VideoSnippet;

import youtubeMiner.Youtube.miner.model.videoSnippet.VideoSnippet;
import youtubeMiner.Youtube.miner.model.videoSnippet.VideoSnippetSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoSnippetService {
    private static final int defaultPageSize = 10;
    @Autowired
    RestTemplate restTemplate;
    public VideoSnippetSearch getComments(String videoId, String nextPageToken, Integer size) {
        String nextPage="";
        if(nextPageToken!=null){
            nextPage=String.format("&pageToken=%s",nextPageToken);
        }
        VideoSnippetSearch search = restTemplate.getForObject(String.format("https://www.googleapis.com/youtube/v3/search?key=AIzaSyC1Q7p1wV3YzryZZnFjp7Nr_cypZ2oLwDg" +
                    "&type=video&channelId=%s&part=snippet%s&maxResults=%d", videoId,nextPage,size),VideoSnippetSearch.class);

        return search;
    }
    public List<VideoSnippet> getNumberVideos(String channelId, Integer number){
        int restantes=number;
        String pageToken=null;
        List<VideoSnippet> videos=new ArrayList<>();
        do {
            int requestedNumber = Integer.min(restantes, defaultPageSize);
            VideoSnippetSearch search = getComments(channelId, pageToken, requestedNumber);
            restantes-=requestedNumber;
            pageToken=search.getNextPageToken();
            videos.addAll(search.getItems());
        }
        while (restantes>0 && pageToken!=null);
        return videos;
    }
}
