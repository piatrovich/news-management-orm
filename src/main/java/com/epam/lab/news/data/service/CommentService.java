package com.epam.lab.news.data.service;

import com.epam.lab.news.data.repo.ICommentRepository;
import com.epam.lab.news.data.repo.PagingAndSortingRepository;
import com.epam.lab.news.data.repo.impl.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

}
