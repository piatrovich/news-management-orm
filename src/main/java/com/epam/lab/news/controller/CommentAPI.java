package com.epam.lab.news.controller;

import com.epam.lab.news.bean.Comment;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.List;

/**
 * API provides possibility for working with comments
 */
@RestController
@RequestMapping("/api/comment")
@Consumes("application/json")
public class CommentAPI {
    @Autowired
    CommentService service;

    /**
     * List of all comments
     *
     * @return Comment list
     */
    @RequestMapping(method = RequestMethod.GET)
    public List showAll(){
        return service.getAll();
    }

    /**
     * One comment
     *
     * @param id Comment id
     * @return Comment
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MappedBean getOne(@PathVariable("id") Long id){
        return service.one(id);
    }

    /**
     * Returns number of all comments without param newsId.<br />
     * If param exists return number of comments for one news
     *
     * @param id News id (optional)
     * @return Count
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long showCount(@RequestParam(value = "newsId", required = false) Long id){
        return service.getCount(id);
    }

    /**
     * Returns list of comment by news id
     *
     * @param id News id
     * @return Comment list
     */
    @RequestMapping(value = "/byNews/{id}", method = RequestMethod.GET)
    public List showByNews(@PathVariable Long id){
        return service.getByNewsId(id);
    }

    /**
     * Return page count. If param exists returns number of pages for one news
     *
     * @param size Page size
     * @param newsId News id (Optional)
     * @return Count
     */
    @RequestMapping(value = "/page/count", method = RequestMethod.GET)
    public Long showPageCount(@RequestParam(value = "size") Long size,
                              @RequestParam(value = "newsId", required = false) Long newsId){
        return service.getPageCount(size, newsId);
    }

    /**
     * Returns particular page of comments
     *
     * @param size Page size
     * @param page Page number
     * @param id News id (optional)
     * @return ResponsePage
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponsePage<MappedBean> showPage(@RequestParam(value = "size") Long size,
                                          @RequestParam(value = "page") Long page,
                                          @RequestParam(value = "newsId", required = false) Long id){
        return service.getPage(new Page(page, size), id);
    }

    /**
     * Saves comment for news
     *
     * @param id News id
     * @param comment comment text
     * @return Saved comment
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public MappedBean addComment(@PathVariable Long id,
                           @RequestBody Comment comment){
        return service.saveComment(comment, id);
    }

    /**
     * Deletes comment
     *
     * @param id Comment id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable("id") Long id){
        service.deleteComment(id);
    }

}
