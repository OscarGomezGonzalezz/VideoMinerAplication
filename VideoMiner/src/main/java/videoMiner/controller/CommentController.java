package videoMiner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import videoMiner.exception.CommentNotFound;
import videoMiner.exception.PageException;
import videoMiner.model.Comment;
import videoMiner.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @GetMapping
    public List<Comment> findAll(@RequestParam(required = false) String order,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) throws PageException{
        Long cuenta = commentRepository.count();

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
        }else {
            paging =  PageRequest.of(page, size);
        }
        Page<Comment> pageComments;
        pageComments=commentRepository.findAll(paging);

        return pageComments.getContent();

    }
    @GetMapping("/{id}")
    public Comment findOne(@PathVariable String id) throws CommentNotFound {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new CommentNotFound();
        }
        return comment.get();

    }
}
