package vimeoMiner.etl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vimeoMiner.model.VideoMiner.*;
import vimeoMiner.service.Caption.CaptionService;
import vimeoMiner.service.Comment.CommentService;
import vimeoMiner.service.Video.VideoService;

@Service
public class ETL {
    @Autowired
    VideoService videoService;
    @Autowired
    CaptionService captionService;
    @Autowired
    CommentService commentService;
    public Channel transformChannel(vimeoMiner.model.Channel.Channel channel, Integer maxVideos, Integer maxComments){
        return new Channel(channel.getId(), channel.getName(), channel.getDescription(), channel.getCreatedTime(), videoService.getNumberVideos(channel.getId(),maxVideos).stream().map(v->transformVideo(v,maxComments)).toList());
    }
    public Video transformVideo(vimeoMiner.model.Video.Video video, Integer maxComments){
        return new Video(video.getId(), video.getName(), video.getDescription(),video.getReleaseTime()
        ,commentService.getNumberComments(video.getId(),maxComments).stream().map(this::transformComment).toList()
        ,captionService.getCaptions(video.getId()).stream().map(x->transformCaption(x)).toList());
    }
    public Comment transformComment(vimeoMiner.model.Comment.Comment comment){
        User author=new User(comment.getUser().getId(),comment.getUser().getName(),comment.getUser().getLink(),comment.getUser().getPictures().getBaseLink());
        return new Comment(comment.getId(), comment.getText(), comment.getCreatedOn(),author);
    }
    public Caption transformCaption(vimeoMiner.model.Caption.Caption caption){
        return new Caption(caption.getId().toString(), caption.getName(),caption.getLanguage());
    }
}
