package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Comment;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.service.CommentService;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MappedBean getOne(@PathVariable("id") Long id){
        return service.one(id);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long showCount(@RequestParam(value = "newsId", required = false) Long id){
        return service.getCount(id);
    }

    @RequestMapping(value = "/byNews/{id}", method = RequestMethod.GET)
    public List showByNews(@PathVariable Long id){
        return service.getByNewsId(id);
    }

    @RequestMapping(value = "/page/count", method = RequestMethod.GET)
    public Long showPageCount(@RequestParam(value = "size") Long size,
                              @RequestParam(value = "newsId", required = false) Long newsId){
        return service.getPageCount(size, newsId);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponsePage<MappedBean> showPage(@RequestParam(value = "size") Long size,
                                          @RequestParam(value = "page") Long page,
                                          @RequestParam(value = "newsId", required = false) Long id){
        return service.getPage(new Page(page, size), id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public MappedBean addComment(@PathVariable Long id,
                           @RequestBody Comment comment){
        return service.saveComment(comment, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable("id") Long id){
        service.deleteComment(id);
    }

}
