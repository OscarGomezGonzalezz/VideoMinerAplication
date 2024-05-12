package youtubeMiner.Youtube.miner.Service.Comments;

import youtubeMiner.Youtube.miner.model.comment.CommentSearch;
import youtubeMiner.Youtube.miner.service.Comments.CommentsService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentsServiceTest {
    @Autowired
    CommentsService commentsService;
    @Test
    @DisplayName("Get comments")
    public void getComments(){
        CommentSearch comments=commentsService.getComments("b7kmP1fsGg8",null,10);
        assertNotNull(comments);
        System.out.println(comments.getItems());
    }

}