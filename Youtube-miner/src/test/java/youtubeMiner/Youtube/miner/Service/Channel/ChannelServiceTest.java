package youtubeMiner.Youtube.miner.Service.Channel;

import youtubeMiner.Youtube.miner.exception.ChannelNotFound;
import youtubeMiner.Youtube.miner.model.channel.Channel;
import youtubeMiner.Youtube.miner.service.Channel.ChannelService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChannelServiceTest {
    @Autowired
    ChannelService channelService;
    @Test
    @DisplayName("Get channel")
    public void getChannel() throws ChannelNotFound {
        Channel channel=channelService.getChannel("UCqECaJ8Gagnn7YCbPEzWH6g");
        assertNotNull(channel);
        System.out.println(channel);
    }

}