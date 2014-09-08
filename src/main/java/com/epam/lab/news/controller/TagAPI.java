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

/**
 * API provides interface for manipulating tags using json
 */
@RestController
@RequestMapping("/api/tag")
@Consumes("application/json")
public class TagAPI {
    @Autowired
    TagService service;

    /**
     * Returns list of tags
     *
     * @param id News id (Optional)
     * @return Tags list
     */
    @RequestMapping(method = RequestMethod.GET)
    public List tags(@RequestParam(value = "newsId", required = false) Long id){
        return service.all(id);
    }

    /**
     * Saves new tag
     * @param tag tag name
     * @return Saved tag
     */
    @RequestMapping(method = RequestMethod.POST)
    public Tag save(@RequestBody Tag tag){
        return service.save(tag);
    }

    /**
     * Return single tag
     *
     * @param id Tag id
     * @return Tag
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MappedBean tag(@PathVariable Long id){
        return service.get(id);
    }

    /**
     * Retuns total tags count
     *
     * @return Tags count
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long count(){
        return service.count();
    }

    /**
     * Deletes tag
     *
     * @param id Tag id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    /**
     * Checks existing tag by id
     *
     * @param id Tag id
     * @return True if exists
     */
    @RequestMapping(value = "/exists/{id}", method = RequestMethod.GET)
    public boolean exists(@PathVariable Long id){
        return service.exists(id);
    }

    /**
     * Checks existing by tag name
     * @param name Tag name
     * @return True if exists
     */
    @RequestMapping(value = "/existsByName", method = RequestMethod.GET)
    public Boolean existsByName(@RequestParam("name") String name){
        return service.existsByName(name);
    }

    /**
     * Returns requested page and page options
     *
     * @param page Page number
     * @param size Page size
     * @return Tags from page
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponsePage<MappedBean> getPage(@RequestParam(value = "page") Long page,
                                     @RequestParam(value = "size") Long size){
        return service.getPage(new Page(page, size));
    }

}
