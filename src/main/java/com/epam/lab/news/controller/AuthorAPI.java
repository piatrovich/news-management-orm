package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.List;

/**
 * API provides possibility for working with authors
 */
@RestController
@RequestMapping("/api/author")
@Consumes("application/json")
public class AuthorAPI {
    @Autowired
    private AuthorService service;

    /**
     * Returns all authors or authors for one news. Depends on param newsId
     *
     * @param id News id (Optional param)
     * @return Authors list
     */
    @RequestMapping(method = RequestMethod.GET)
    public List showAllAuthors(@RequestParam(value = "newsId", required = false) Long id){
        return service.getAllAuthors(id);
    }

    /**
     * Returns author with id after saving
     *
     * @param author Name of author
     * @return Updated object
     */
    @RequestMapping(method = RequestMethod.POST)
    public Author saveAuthor(@RequestBody Author author){
        return service.saveAuthor(author);
    }

    /**
     * Deletes author
     *
     * @param id Author id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAuthor(@PathVariable Long id){
        service.deleteAuthor(id);
    }

    /**
     * Returns one author
     *
     * @param id Author id
     * @return Author
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author showOneAuthor(@PathVariable Long id){
        return service.getSingleAuthor(id);
    }

    /**
     * Checks if author already exists
     *
     * @param id Author id
     * @return True if exists, otherwise false
     */
    @RequestMapping(value = "/exists", method = RequestMethod.GET)
    public Boolean showExists(@RequestParam("id") Long id){
        return service.checkExists(id);
    }

    /**
     * Checks if author exists with name
     *
     * @param name Author name
     * @return True if exists, otherwise false
     */
    @RequestMapping(value = "/existsByName", method = RequestMethod.GET)
    public Boolean existsByName(@RequestParam("name") String name){
        return service.existsByName(name);
    }

    /**
     * Returns count of all authors
     *
     * @return Number of authors
     */
    @RequestMapping(value = "count", method = RequestMethod.GET)
    public Long count(){
        return service.getCount();
    }

    /**
     * Returns authors from page
     *
     * @param page Author page
     * @param size Page size
     * @return ResponsePage
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponsePage<MappedBean> getPage(@RequestParam(value = "page") Long page,
                                            @RequestParam(value = "size") Long size){
        return service.getPage(new Page(page, size));
    }

}
