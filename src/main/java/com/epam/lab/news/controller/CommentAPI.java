package com.epam.lab.news.controller;

import com.epam.lab.news.data.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
