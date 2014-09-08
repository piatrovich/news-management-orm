package com.epam.lab.news.controller;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.List;

@RestController
@RequestMapping("/api/tag")
@Consumes("application/json")
public class TagAPI {
    @Autowired
    TagService service;

    @RequestMapping(method = RequestMethod.GET)
    public List tags(@RequestParam(value = "newsId", required = false) Long id){
        return service.all(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Tag save(@RequestBody Tag tag){
        return service.save(tag);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MappedBean tag(@PathVariable Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long count(){
        return service.count();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @RequestMapping(value = "/exists/{id}", method = RequestMethod.GET)
    public boolean exists(@PathVariable Long id){
        return service.exists(id);
    }

    @RequestMapping(value = "/existsByName", method = RequestMethod.GET)
    public Boolean existsByName(@RequestParam("name") String name){
        return service.existsByName(name);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponsePage<MappedBean> getPage(@RequestParam(value = "page") Long page,
                                     @RequestParam(value = "size") Long size){
        return service.getPage(new Page(page, size));
    }

}
