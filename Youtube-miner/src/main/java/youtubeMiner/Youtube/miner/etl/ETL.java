package youtubeMiner.Youtube.miner.etl;

import youtubeMiner.Youtube.miner.model.VideoMiner.*;
import youtubeMiner.Youtube.miner.model.caption.CaptionSnippet;
import youtubeMiner.Youtube.miner.model.channel.ChannelSnippet;
import youtubeMiner.Youtube.miner.model.comment.CommentSnippet__1;
import youtubeMiner.Youtube.miner.model.videoSnippet.VideoSnippet;
import youtubeMiner.Youtube.miner.model.videoSnippet.VideoSnippetDetails;
import youtubeMiner.Youtube.miner.service.Caption.CaptionService;
import youtubeMiner.Youtube.miner.service.Comments.CommentsService;
import youtubeMiner.Youtube.miner.service.VideoSnippet.VideoSnippetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ETL {
    @Autowired
    VideoSnippetService videoService;
    @Autowired
    CommentsService commentsService;
    @Autowired
    CaptionService captionService;
    public Channel transformChannel(youtubeMiner.Youtube.miner.model.channel.Channel channel, Integer maxVideos, Integer maxComments){
        ChannelSnippet channelSnippet= channel.getSnippet();
        return new Channel(channel.getId(),channelSnippet.getTitle(),
                channelSnippet.getDescription(),channelSnippet.getPublishedAt(),
                videoService.getNumberVideos(channel.getId(),maxVideos).stream().map(v->transformVideo(v,maxComments)).toList());
    }
    public Video transformVideo(VideoSnippet video,Integer maxComments){
        VideoSnippetDetails details=video.getSnippet();
        return new Video(video.getId().getVideoId(),details.getTitle(), details.getDescription(),details.getPublishedAt(),
                commentsService.getNumberComments(video.getId().getVideoId(),maxComments).stream().map(c->transformComment(c)).toList(),
                captionService.getCaption(video.getId().getVideoId()).stream().map(c->transformCaption(c)).toList());
    }

    public Caption transformCaption(youtubeMiner.Youtube.miner.model.caption.Caption caption) {
        CaptionSnippet captionSnippet = caption.getSnippet();
        return new Caption(caption.getId(),captionSnippet.getName(), captionSnippet.getLanguage());
    }

    public Comment transformComment(youtubeMiner.Youtube.miner.model.comment.Comment comment) {
        CommentSnippet__1 commentSnippet = comment.getCommentSnippet().getTopLevelComment().getSnippet();
        User author=new User(commentSnippet.getAuthorDisplayName(),
                commentSnippet.getAuthorChannelUrl(),commentSnippet.getAuthorProfileImageUrl());
        return new Comment(comment.getId(), commentSnippet.getTextOriginal(),
                commentSnippet.getPublishedAt(),author);
    }

}
