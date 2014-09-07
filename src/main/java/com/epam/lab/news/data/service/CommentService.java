package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.Comment;
import com.epam.lab.news.bean.News;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.ICommentRepository;
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
    @Qualifier("commentRepository")
    ICommentRepository repository;

    public List getAll(){
        return repository.all();
    }

    public Long getCount(){
        return repository.count();
    }

    public List getByNewsId(Long id){
        return repository.findByNewsId(id);
    }

    public Long getPageCountByNewsId(Long pageSize, Long newsId){
        Long total = repository.getCountByNewsId(newsId);
        if (pageSize != 0L){
            Long count = total / pageSize;
            return total % pageSize > 0 ? ++count : count;
        } else {
            return 0L;
        }
    }

    public void saveComment(Comment comment, Long newsId){
        News news = new News();
        news.setId(newsId);
        comment.setNews(news);
        comment.setCreationDate(new Date());
        repository.save(comment);
    }

    public ResponsePage<Comment> getPage(Page page, Long...args){
        page.setTotal(repository.pageCount(page.getSize()));
        List<Comment> items = null;
        if (page.getCurrent() > 0 && page.getCurrent() <= page.getTotal()) {
            items = repository.page(page, args);
        }
        return new ResponsePage<Comment>(items, page);
    }

}
