package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
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

    @RequestMapping(method = RequestMethod.POST)
    public Author saveAuthor(@RequestBody Author author){
        return service.saveAuthor(author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAuthor(@PathVariable Long id){
        service.deleteAuthor(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author showOneAuthor(@PathVariable Long id){
        return service.getSingleAuthor(id);
    }

    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public Boolean showExists(@RequestParam("id") Long id){
        return service.checkExists(id);
    }

    @RequestMapping(value = "count", method = RequestMethod.GET)
    public Long count(){
        return service.getCount();
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponsePage<MappedBean> getPage(@RequestParam(value = "page") Long page,
                                            @RequestParam(value = "size") Long size){
        return service.getPage(new Page(page, size));
    }

}
