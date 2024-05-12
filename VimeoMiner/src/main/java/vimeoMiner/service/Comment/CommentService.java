package vimeoMiner.service.Comment;


import vimeoMiner.model.Comment.Comment;
import vimeoMiner.model.Comment.CommentSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private static final Integer defaultPageSize=20;
    @Autowired
    RestTemplate restTemplate;
    public CommentSearch getComments(String video, Integer page, Integer pageSize){
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization","Bearer 858610e23a94d8956a8de5bbf8e28e8c");
        HttpEntity<CommentSearch> req=new HttpEntity<>(null,headers);
        HttpEntity<CommentSearch> search=restTemplate.exchange(String.format("https://api.vimeo.com/videos/%s/comments?per_page=%d&page=%d",video,pageSize,page), HttpMethod.GET,req, CommentSearch.class);
        return search.getBody();
    }
    public List<Comment> getNumberComments(String video, Integer number){
        int rest=number;
        int page=1;
        boolean next=true;
        List<Comment> comments=new ArrayList<>();
        while(rest>0 && next){
            int requestedComments=Integer.min(rest, defaultPageSize);
            CommentSearch search=getComments(video,page,requestedComments);
            comments.addAll(search.getComments());
            next=search.getPaging().getNext()!=null;
            page++;
            rest-=requestedComments;
        }
        return comments;
    }
}
