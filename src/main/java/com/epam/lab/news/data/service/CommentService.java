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

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CommentService {
    @Autowired
    @Qualifier("CommentRepository")
    CommentRepository repository;

    public List getAll(){
        return repository.all();
    }

    public MappedBean one(Long id){
        return repository.one(id);
    }

    public Long getCount(Long...params){
        return repository.count(params);
    }

    public List getByNewsId(Long id){
        return repository.all(id);
    }

    public Long getPageCount(Long pageSize, Long...params){
        Long total = repository.count(params);
        if (pageSize != 0L){
            Long count = total / pageSize;
            return total % pageSize > 0 ? ++count : count;
        } else {
            return 0L;
        }
    }

    public MappedBean saveComment(Comment comment, Long newsId){
        News news = new News();
        news.setId(newsId);
        comment.setNews(news);
        comment.setCreationDate(new Date());
        return repository.save(comment);
    }

    public ResponsePage<MappedBean> getPage(Page page, Long...params){
        page.setTotal(getPageCount(page.getSize(), params));
        List<MappedBean> items = repository.page(page, params);
        return new ResponsePage<MappedBean>(items, page);
    }

    public void deleteComment(Long id){
        MappedBean comment = repository.one(id);
        if (comment != null)
            repository.delete(comment);
    }

}
