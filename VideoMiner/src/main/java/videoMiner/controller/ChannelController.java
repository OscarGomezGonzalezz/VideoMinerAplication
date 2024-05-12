package videoMiner.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import videoMiner.exception.ChannelNotFound;
import videoMiner.exception.PageException;
import videoMiner.model.Channel;
import videoMiner.repository.ChannelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/channels")
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

    // GET http://localhost:8080/api/projects
    @GetMapping
    public List<Channel> findAll(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String order,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) throws PageException {
        Long cuenta = channelRepository.count();

        if(!cuenta.equals(0L) && page >= Math.ceil((double)cuenta/size) ){
            throw new PageException();
        }
        Pageable paging;
        if(name!=null){
            if(order.startsWith("-")){
                paging= PageRequest.of(page,size, Sort.by(order.substring(1)).descending());
            }
            else{
                paging=PageRequest.of(page,size,Sort.by(order).ascending());
                }
            }
            else{
                paging=PageRequest.of(page,size);
            }
            Page<Channel> pageProjects;
            if(name==null){
                pageProjects=channelRepository.findAll(paging);
            }
            else{
                pageProjects=channelRepository.findByName(name,paging);
            }
            return pageProjects.getContent();
        }

    @GetMapping("/{id}")
    public Channel findOne(@PathVariable String id) throws ChannelNotFound {
        Optional<Channel> channel = channelRepository.findById(id);
        if (channel.isEmpty()) {
            throw new ChannelNotFound();
        }
        return channel.get();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Channel createChannel(@RequestBody @Valid Channel channel) {
        return channelRepository.save(channel);
    }


}