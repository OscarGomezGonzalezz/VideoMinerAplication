package vimeoMiner.service.Caption;


import vimeoMiner.model.Caption.Caption;
import vimeoMiner.model.Caption.CaptionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class CaptionService {
    @Autowired
    RestTemplate restTemplate;
    public List<Caption> getCaptions(String video){
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization","Bearer 858610e23a94d8956a8de5bbf8e28e8c");//añadimos el token directamente en la cabecera
                                                                                                    //para que no sea necesario en las pruebas postman
        HttpEntity<CaptionSearch> req=new HttpEntity<>(null,headers);
        HttpEntity<CaptionSearch> search=restTemplate.exchange(String.format("https://api.vimeo.com/videos/%s/texttracks",video), HttpMethod.GET,req, CaptionSearch.class);
        return search.getBody().getCaptions();
    }
}


