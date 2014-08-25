package com.epam.lab.news.data.service;

import com.epam.lab.news.data.repo.PagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    @Qualifier("commentRepository")
    PagingAndSortingRepository repository;

    public List getAll(){
        return repository.all();
    }

}
