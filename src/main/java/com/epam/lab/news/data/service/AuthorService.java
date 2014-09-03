package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.data.repo.PagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class AuthorService {
    @Autowired
    @Qualifier("authorRepository")
    private PagingAndSortingRepository repository;

    public Iterable<Author> getAllAuthors(){
        return repository.all();
    }

    public Author getSingleAuthor(Long id){
        return (Author) repository.one(id);
    }

    public Boolean checkExists(Long id){
        return repository.exists(id);
    }

}
