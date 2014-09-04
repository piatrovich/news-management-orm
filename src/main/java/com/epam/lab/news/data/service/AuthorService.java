package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.impl.BasePagingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class AuthorService {
    @Autowired
    @Qualifier("AuthorRepository")
    private BasePagingRepositoryImpl repository;

    public List<Author> getAllAuthors(){
        return (List) repository.all();
    }

    public Author getSingleAuthor(Long id){
        return (Author) repository.one(id);
    }

    public Boolean checkExists(Long id){
        return repository.exists(id);
    }

    public Author saveAuthor(Author author){
        return (Author) repository.save(author);
    }

    public void deleteAuthor(Long id){
        Author author = (Author) repository.one(id);
        if(author != null){
            repository.delete(author);
        }
    }

    public ResponsePage<MappedBean> getPage(Page page){
        page.setTotal(repository.pageCount(page.getSize()));
        List<MappedBean> items = null;
        if (page.getCurrent() > 0 && page.getCurrent() <= page.getTotal()) {
            items = repository.page(page);
        }
        return new ResponsePage<MappedBean>(items, page);
    }

    public Long getCount(){
        return repository.count();
    }

}
