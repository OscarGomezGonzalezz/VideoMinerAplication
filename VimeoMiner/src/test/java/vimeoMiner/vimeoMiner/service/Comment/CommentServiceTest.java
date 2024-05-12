package vimeoMiner.vimeoMiner.service.Comment;

import vimeoMiner.model.Comment.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vimeoMiner.service.Comment.CommentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Test
    @DisplayName("Get comments of video")
    public void getComments(){
        Integer num=238;
        List<Comment> comments=commentService.getNumberComments("333849954",num);
        assertEquals(num,comments.size());
        System.out.println(comments);
    }

}