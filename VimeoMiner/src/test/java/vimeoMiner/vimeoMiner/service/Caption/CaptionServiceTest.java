package vimeoMiner.vimeoMiner.service.Caption;

import vimeoMiner.model.Caption.Caption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vimeoMiner.service.Caption.CaptionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class CaptionServiceTest {
    @Autowired
    CaptionService captionService;
    @Test
    @DisplayName("Get captions of video")
    public void getCaptions(){
        List<Caption> captions=captionService.getCaptions("752144096");
        assertNotNull(captions);
        System.out.println(captions);
    }
}