package vimeoMiner.vimeoMiner.service.Video;

import vimeoMiner.model.Video.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vimeoMiner.service.Video.VideoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class VideoServiceTest {
    @Autowired
    VideoService videoService;
    @Test
    @DisplayName("Get videos of channel")
    public void getVideos(){
        Integer number=387;
        List<Video> videos=videoService.getNumberVideos("1170284",number);
        assertEquals(number,videos.size());
        System.out.println(videos);
    }
}