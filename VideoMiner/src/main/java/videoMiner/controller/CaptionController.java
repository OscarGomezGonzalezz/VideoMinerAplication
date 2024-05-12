package videoMiner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import videoMiner.exception.CaptionNotFound;
import videoMiner.exception.PageException;
import videoMiner.model.Caption;
import videoMiner.repository.CaptionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/captions")
public class CaptionController {

    @Autowired
    CaptionRepository captionRepository;

    public CaptionController(CaptionRepository captionRepository) {
        this.captionRepository = captionRepository;
    }

    @GetMapping
    public List<Caption> findAll(@RequestParam(required = false) String order,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) throws PageException {
        Long cuenta = captionRepository.count();

        if(!cuenta.equals(0L) && page >= Math.ceil((double)cuenta/size) ){
            throw new PageException();
        }

        Pageable paging;
        if(order != null){
            if(order.startsWith("-")){
                paging= PageRequest.of(page,size, Sort.by(order.substring(1)).descending());
            }
            else{
                paging=PageRequest.of(page,size,Sort.by(order).ascending());
            }
        }else{
            paging =  PageRequest.of(page, size);
        }

        Page<Caption> pageCaptions;
        pageCaptions=captionRepository.findAll(paging);

        return pageCaptions.getContent();
    }

    // GET http://localhost:8080/api/projects/{id}
    @GetMapping("/{id}")
    public Caption findOne(@PathVariable String id) throws CaptionNotFound {
        Optional<Caption> caption = captionRepository.findById(id);
        if (caption.isEmpty()) {
            throw new CaptionNotFound();
        }
        return caption.get();
    }

}
