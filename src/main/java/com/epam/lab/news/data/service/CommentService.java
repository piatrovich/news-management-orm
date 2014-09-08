package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.Comment;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.News;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.impl.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Comment service
 *
 * @author Dzmitry Piatrovich
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CommentService {
    @Autowired
    @Qualifier("CommentRepository")
    CommentRepository repository;

    /**
     * Returns list of all comments
     *
     * @return
     */
    public List getAll(){
        return repository.all();
    }

    /**
     * Returns one comment
     *
     * @param id Comment id
     * @return Comment
     */
    public MappedBean one(Long id){
        return repository.one(id);
    }

    /**
     * Returns comments count
     *
     * @param params News id for view only for single news
     * @return Comment count
     */
    public Long getCount(Long...params){
        return repository.count(params);
    }

    /**
     * Returns list of comment for news
     *
     * @param id News id
     * @return Comment list
     */
    public List getByNewsId(Long id){
        return repository.all(id);
    }

    /**
     * returns comment page count
     *
     * @param pageSize Page size
     * @param params news id for view comments only for this news
     * @return Comment page count
     */
    public Long getPageCount(Long pageSize, Long...params){
        Long total = repository.count(params);
        if (pageSize != 0L){
            Long count = total / pageSize;
            return total % pageSize > 0 ? ++count : count;
        } else {
            return 0L;
        }
    }

    /**
     * Saves comment to news
     *
     * @param comment Comment
     * @param newsId News id
     * @return Saved comment
     */
    public MappedBean saveComment(Comment comment, Long newsId){
        News news = new News();
        news.setId(newsId);
        comment.setNews(news);
        comment.setCreationDate(new Date());
        return repository.save(comment);
    }

    /**
     * Returns comment page
     *
     * @param page page
     * @param params news id for view comments only for this news
     * @return Comment page
     */
    public ResponsePage<MappedBean> getPage(Page page, Long...params){
        page.setTotal(getPageCount(page.getSize(), params));
        List<MappedBean> items = repository.page(page, params);
        return new ResponsePage<MappedBean>(items, page);
    }

    /**
     * Deletes comment
     *
     * @param id Comment id
     */
    public void deleteComment(Long id){
        MappedBean comment = repository.one(id);
        if (comment != null)
            repository.delete(comment);
    }

}
