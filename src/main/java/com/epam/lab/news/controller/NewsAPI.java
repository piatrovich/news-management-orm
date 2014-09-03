package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.News;
import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.CRUDRepository;
import com.epam.lab.news.data.service.NewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import java.sql.Connection;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@Consumes("application/json")
public class NewsAPI {
    @Autowired
    NewsService service;

    @RequestMapping(method = RequestMethod.GET)
    public List news(){
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody News news){
        //service.save(news);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long newsCount(){
        return service.getTotalNewsCount();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public News single(@PathVariable Long id){
        return service.getSingle(id);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponsePage<MappedBean> getPage(@RequestParam(value = "page") Long page,
                                     @RequestParam(value = "size") Long size){
        return service.getPage(new Page(page, size));
    }

    @RequestMapping(value = "/{id}/tag", method = RequestMethod.POST)
    public void addTag(@PathVariable Long id, @RequestBody Tag tag){
        service.addTag(id, tag);
    }

    @RequestMapping(value = "/{id}/author", method = RequestMethod.POST)
    public void addAuthor(@PathVariable Long id, @RequestBody Author author){
        service.addAuthor(id, author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNews(@PathVariable Long id){
        service.deleteNews(id);
    }

}
