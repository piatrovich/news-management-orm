package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.News;
import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.service.NewsService;
import com.epam.lab.news.validation.ArticleValidator;
import com.epam.lab.news.validation.ValidationResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.List;

/**
 * API provides interface for working with news
 */
@RestController
@RequestMapping("/api/news")
@Consumes("application/json")
public class NewsAPI {
    private Logger logger = Logger.getLogger("errors");

    @Autowired
    NewsService service;

    /** Wiring validator for articles */
    @Autowired
    ArticleValidator validator;

    /**
     * Returns all newses
     *
     * @return News list
     */
    @RequestMapping(method = RequestMethod.GET)
    public List news(){
        return service.getAll();
    }

    /**
     * Saves new news
     *
     * @param news News data
     * @return Result and errors
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ValidationResult save(@RequestBody News news){
        ValidationResult result = validator.validate(news);
        if (result.isStatus()){
            service.saveNews(news);
        }
        return result;
    }

    /**
     * Updates news
     *
     * @param news News data
     * @return Result and errors if exists
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody ValidationResult update(@RequestBody News news){
        ValidationResult result = validator.validate(news);
        if (result.isStatus()){
            service.updateNews(news);
        }
        return result;
    }

    /**
     * Returns total news count
     *
     * @return News count
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long newsCount(){
        return service.getTotalNewsCount();
    }

    /**
     * Returns single news
     *
     * @param id News id
     * @return Actual news
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public News single(@PathVariable Long id){
        return service.getSingle(id);
    }

    /**
     * Returns news page
     *
     * @param page Page number
     * @param size Page size
     * @return Result and errors if exists
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponsePage<MappedBean> getPage(@RequestParam(value = "page") Long page,
                                     @RequestParam(value = "size") Long size){
        return service.getPage(new Page(page, size));
    }

    /**
     * Adds tag to news
     *
     * @param id News id
     * @param tag tag name
     */
    @RequestMapping(value = "/{id}/tag", method = RequestMethod.POST)
    public void addTag(@PathVariable Long id, @RequestBody Tag tag){
        service.addTag(id, tag);
    }

    /**
     * Adds author to news
     *
     * @param id News id
     * @param author Author name
     */
    @RequestMapping(value = "/{id}/author", method = RequestMethod.POST)
    public void addAuthor(@PathVariable Long id, @RequestBody Author author){
        service.addAuthor(id, author);
    }

    /**
     * Deletes one news
     *
     * @param id News id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNews(@PathVariable Long id){
        service.deleteNews(id);
    }

    /**
     * Returns top commented news
     *
     * @param id Size of top
     * @return News list
     */
    @RequestMapping(value = "/top/{id}", method = RequestMethod.GET)
    public List mostCommented(@PathVariable("id") Long id){
        return service.getMostCommented(id);
    }

    /**
     * Returns number of news by tag
     *
     * @param id Tag id
     * @return News count
     */
    @RequestMapping(value = "/countByTag", method = RequestMethod.GET)
    public Integer countByTag(@RequestParam("tagId") Long id){
        return service.getCountByTag(id);
    }

    /**
     * Returns news list by tag
     *
     * @param id Tag id
     * @return News list
     */
    @RequestMapping(value = "/newsByTag", method = RequestMethod.GET)
    public List<MappedBean> newsByTag(@RequestParam("tagId") Long id){
        return service.newsByTag(id);
    }

    /**
     * Returns number of news by author
     *
     * @param id Author id
     * @return News count
     */
    @RequestMapping(value = "/countByAuthor", method = RequestMethod.GET)
    public Integer countByAuthor(@RequestParam("authorId") Long id){
        return service.getCountByAuthor(id);
    }

    /**
     * Returns news list by author
     *
     * @param id Author id
     * @return News list
     */
    @RequestMapping(value = "/newsByAuthor", method = RequestMethod.GET)
    public List<MappedBean> newsByAuthor(@RequestParam("authorId") Long id){
        return service.newsByAuthor(id);
    }

}
