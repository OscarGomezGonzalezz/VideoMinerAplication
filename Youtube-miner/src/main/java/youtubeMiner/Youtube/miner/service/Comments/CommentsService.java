package youtubeMiner.Youtube.miner.service.Comments;

import youtubeMiner.Youtube.miner.model.comment.Comment;
import youtubeMiner.Youtube.miner.model.comment.CommentSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsService {
    private static final int defaultPageSize = 10;
    @Autowired
    RestTemplate restTemplate;
    public CommentSearch getComments(String videoId,String pageToken,Integer size) {
        String pageTokenUri="";
        if(pageToken!=null){
            pageTokenUri=String.format("&pageToken=%s",pageToken);
        }
        CommentSearch search;
        try {
            search = restTemplate.getForObject(String.format("https://www.googleapis.com/youtube/v3/commentThreads?" +
                    "videoId=%s&key=AIzaSyC1Q7p1wV3YzryZZnFjp7Nr_cypZ2oLwDg&part=snippet%s&maxResults=%d", videoId,pageTokenUri,size),CommentSearch.class);
        }
        catch(HttpClientErrorException.Forbidden e){
            search=new CommentSearch(null,List.of());
        }
        return search;
    }
    public List<Comment> getNumberComments(String videoId,Integer number){
        int restantes=number;
        String pageToken=null;
        List<Comment> comments=new ArrayList<>();
        do {
            int requestedNumber = Integer.min(restantes, defaultPageSize);
            CommentSearch search = getComments(videoId, pageToken, requestedNumber);
            restantes-=requestedNumber;
            pageToken=search.getNextPageToken();
            comments.addAll(search.getItems());
        }
        while (restantes>0 && pageToken!=null);
        return comments;
    }
}

