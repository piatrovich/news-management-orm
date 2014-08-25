package com.epam.lab.news.controller;

import com.epam.lab.news.bean.News;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.CRUDRepository;
import com.epam.lab.news.data.service.NewsService;
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
        //System.out.println(news.toString());
        //service.save(news);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public News single(@PathVariable Long id){
        return service.getSingle(id);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponsePage<News> getPage(@RequestParam(value = "page") Long page,
                                     @RequestParam(value = "size") Long size){
        return service.getPage(new Page(page, size));
    }

}
