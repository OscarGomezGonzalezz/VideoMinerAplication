package youtubeMiner.Youtube.miner.controller;

import youtubeMiner.Youtube.miner.exception.ChannelNotFound;
import youtubeMiner.Youtube.miner.model.VideoMiner.Channel;
import youtubeMiner.Youtube.miner.model.VideoMiner.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class YoutubeControllerTest {
    @Autowired
    YoutubeController controller;
    @Test
    @DisplayName("Get channel")
    public void getChannel() throws ChannelNotFound {
        Channel channel=controller.getChannel("UCqECaJ8Gagnn7YCbPEzWH6g",2,10);
        assertNotNull(channel);
        assertEquals(2,channel.getVideos().size());
        for(Video video:channel.getVideos()){
            assertTrue(video.getComments().size()<=10);
        }
        System.out.println(channel);
    }
}