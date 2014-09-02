package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.CRUDRepository;
import com.epam.lab.news.data.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.List;

@RestController
@RequestMapping("/api/tag")
@Consumes("application/json")
@SuppressWarnings("unchecked")
public class TagAPI {
    @Autowired
    @Qualifier("tagRepository")
    CRUDRepository repository;

    @Autowired
    TagService service;

    @RequestMapping(method = RequestMethod.GET)
    public List tags(){
        return repository.all();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Tag save(@RequestBody Tag tag){
        repository.save(tag);
        return tag;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Tag tag(@PathVariable Long id){
        return (Tag) repository.one(id);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long count(){
        return repository.count();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @RequestMapping(value = "/exists/{id}", method = RequestMethod.GET)
    public boolean exists(@PathVariable Long id){
        return repository.exists(id);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponsePage<Tag> getPage(@RequestParam(value = "page") Long page,
                                     @RequestParam(value = "size") Long size){
        return service.getPage(new Page(page, size));
    }

}
