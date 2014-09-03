package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;

@RestController
@RequestMapping("/api/author")
@Consumes("application/json")
public class AuthorAPI {
    @Autowired
    private AuthorService service;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Author> showAllAuthors(){
        return service.getAllAuthors();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author showOneAuthor(@PathVariable Long id){
        return service.getSingleAuthor(id);
    }

    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public Boolean showExists(@RequestParam("id") Long id){
        return service.checkExists(id);
    }

}
