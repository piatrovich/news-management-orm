package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.data.repo.CRUDRepository;
import com.epam.lab.news.data.repo.PagingAndSortingRepository;
import com.epam.lab.news.data.repo.impl.BasePagingAndSortingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class AuthorService {
    @Autowired
    @Qualifier("AuthorRepository")
    private BasePagingAndSortingRepositoryImpl repository;

    public List<Author> getAllAuthors(){
        return (List) repository.all();
    }

    public Author getSingleAuthor(Long id){
        return (Author) repository.one(id);
    }

    public Boolean checkExists(Long id){
        return repository.exists(id);
    }

}
