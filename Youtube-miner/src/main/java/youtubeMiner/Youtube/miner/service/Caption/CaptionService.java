package youtubeMiner.Youtube.miner.service.Caption;

import youtubeMiner.Youtube.miner.model.caption.Caption;
import youtubeMiner.Youtube.miner.model.caption.CaptionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CaptionService {
    @Autowired
    RestTemplate restTemplate;
    public List<Caption> getCaption(String idVideo){
        List<Caption> captions = restTemplate.getForObject(String.format("https://www.googleapis.com/youtube/v3/captions?" +
                    "videoId=%s&key=AIzaSyC1Q7p1wV3YzryZZnFjp7Nr_cypZ2oLwDg&part=snippet",idVideo),CaptionSearch.class).getItems();
        return captions;
    }
}
