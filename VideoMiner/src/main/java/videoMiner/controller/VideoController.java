package videoMiner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import videoMiner.exception.PageException;
import videoMiner.exception.VideoNotFound;
import videoMiner.model.Video;
import videoMiner.repository.VideoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/videos")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;

    // GET http://localhost:8080/api/projects
    @GetMapping
    public List<Video> findAll(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String order,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) throws PageException {

        Long cuenta = videoRepository.count();

        if(!cuenta.equals(0L) && page >= Math.ceil((double)cuenta/size) ){
            throw new PageException();
        }

        Pageable paging;
        if(name == null){
            if(order != null){
                if(order.startsWith("-")){
                    paging= PageRequest.of(page,size, Sort.by(order.substring(1)).descending());
                }
                else{
                    paging=PageRequest.of(page,size,Sort.by(order).ascending());
                }
            }else{
                paging = PageRequest.of(page,size);
            }
        } else{
            paging = PageRequest.of(page,size);
        }
        Page<Video> pageVideos;
        if(name==null){
            pageVideos=videoRepository.findAll(paging);
        }
        else{
            pageVideos=videoRepository.findByName(name,paging);
        }
        return pageVideos.getContent();
    }

    // GET http://localhost:8080/api/projects/{id}
    @GetMapping("/{id}")
    public Video findOne(@PathVariable String id) throws VideoNotFound {
        // TODO: COMPLETE
        Optional<Video> video = videoRepository.findById(id);
        if (video.isEmpty()) {
            throw new VideoNotFound();
        }
        return video.get();
    }
}
