package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.service.INewsService;
import com.epam.lab.news.exception.bean.ControllerException;
import com.epam.lab.news.exception.bean.ServiceException;
import com.epam.lab.news.validation.ArticleValidator;
import com.epam.lab.news.validation.ValidationResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;

/**
 * Provides public API for working with service
 *
 * @author Dzmitry Piatrovich
 */
@RestController
@RequestMapping("/api/")
@Consumes("application/json")
@PropertySource("classpath:logger.properties")
public class APIController {
    /** Error logger */
    Logger logger = Logger.getLogger("errors");

    /** Service for working with data using repositories */
    @Autowired
    @Qualifier("daoService")
    INewsService newsService;

    /** Wiring environment for access to messages */
    @Autowired
    Environment environment;

    /** Wiring validator for articles */
    @Autowired
    ArticleValidator validator;

    /**
     * Returns all articles as JSONs
     *
     * @return JSON array
     */
    @RequestMapping(value = "/all")
    public Iterable<Article> getArticles() {
        Iterable<Article> articles = null;
        try {
            articles = newsService.getAll();
        } catch (ServiceException e) {
            logger.error(environment.getProperty("error.controller.get.all"), e);
        }
        return articles;
    }

    /**
     * Returns single article in JSON
     *
     * @param id Unique article id
     * @return Article object
     */
    @RequestMapping(value = "/get/{id}")
    public Article getArticleForView(@PathVariable Long id){
        Article article = null;
        try {
            article = newsService.get(id);
        } catch (ServiceException e) {
            logger.error(environment.getProperty("error.controller.get"), e);
            throw new ControllerException();
        }
        return article;
    }

    /**
     * Saves new article
     *
     * @param article Parsed article object from JSON request body
     */
    @RequestMapping(value = "/add")
    public @ResponseBody ValidationResult newArticle(@RequestBody Article article){
        ValidationResult result = validator.validate(article);
        if(result.isStatus()){
            try {
                newsService.save(article);
            } catch (ServiceException e) {
                logger.error(environment.getProperty("error.controller.add"), e);
            }
        }
        return result;
    }

    /**
     * Updating existing article
     *
     * @param article  Parsed article object from JSON request body
     */
    @RequestMapping(value = "/update")
    public @ResponseBody ValidationResult updateArticle(@RequestBody Article article){
        ValidationResult result = validator.validate(article);
        if(result.isStatus()){
            try {
                newsService.update(article);
            } catch (ServiceException e) {
                logger.error(environment.getProperty("error.controller.update"), e);
            }
        }
        return result;
    }

    /**
     * Deleting existing article
     *
     * @param id Unique article id
     */
    @RequestMapping(value = "/delete/{id}")
    public void deleteArticle(@PathVariable Long id){
        try {
            newsService.delete(id);
        } catch (ServiceException e) {
            logger.error(environment.getProperty("error.controller.delete"), e);
        }
    }

}