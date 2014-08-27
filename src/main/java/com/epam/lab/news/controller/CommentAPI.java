package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Comment;
import com.epam.lab.news.data.service.CommentService;
import com.epam.lab.news.data.service.NewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
@Consumes("application/json")
public class CommentAPI {
    @Autowired
    CommentService service;

    @RequestMapping(method = RequestMethod.GET)
    public List showAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long showCount(){
        return service.getCount();
    }

    @RequestMapping(value = "/byNews/{id}", method = RequestMethod.GET)
    public List showByNews(@PathVariable Long id){
        return service.getByNewsId(id);
    }

    @RequestMapping(value = "/page/count", method = RequestMethod.GET)
    public Long showPageCount(@RequestParam(value = "size") Long size,
                              @RequestParam(value = "newsId") Long newsId){
        return service.getPageCountByNewsId(size, newsId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void addComment(@PathVariable Long id,
                           @RequestBody Comment comment){
        service.saveComment(comment, id);
    }

}
