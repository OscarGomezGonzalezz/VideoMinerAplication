package vimeoMiner.vimeoMiner.service.Channel;

import vimeoMiner.exception.ChannelNotFound;
import vimeoMiner.model.Channel.Channel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vimeoMiner.service.Channel.ChannelService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class ChannelServiceTest {
    @Autowired
    ChannelService channelService;
    @Test
    @DisplayName("Get channel")
    public void getChannel() throws ChannelNotFound {
        Channel channel= channelService.getChannel(10);
        assertNotNull(channel);
        System.out.println(channel);
    }
    @Test
    @DisplayName("Should return 404 when channel doesen´t exist")
    public void getChannelNotFound() throws ChannelNotFound {
       Channel channel;
        try{
            channel= channelService.getChannel(1000);
        }
       catch(ChannelNotFound e){
            channel=null;
       }
        assertNull(channel);
    }
}