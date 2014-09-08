package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.impl.AuthorRepository;
import com.epam.lab.news.data.repo.impl.BasePagingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer
 */
@Service
@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.REQUIRED)
public class AuthorService {
    @Autowired
    @Qualifier("AuthorRepository")
    AuthorRepository repository;

    /**
     * Return author list
     *
     * @param params For view list author for single news
     * @return Author list
     */
    public List<Author> getAllAuthors(Long...params){
        return (List) repository.all(params);
    }

    /**
     * Returns author
     *
     * @param id Author id
     * @return Author
     */
    public Author getSingleAuthor(Long id){
        return (Author) repository.one(id);
    }

    /**
     * Checks author existing
     *
     * @param id Author id
     * @return True if exists
     */
    public Boolean checkExists(Long id){
        return repository.exists(id);
    }

    /**
     *
     * Checks author existing
     *
     * @param name Author name
     * @return True if exists
     */
    public Boolean existsByName(String name){
        return repository.existByName(name);
    }

    /**
     * Saves author
     *
     * @param author Author
     * @return Saved author
     */
    public Author saveAuthor(Author author){
        return (Author) repository.save(author);
    }

    /**
     * Deletes author by id
     *
     * @param id Author id
     */
    public void deleteAuthor(Long id){
        Author author = (Author) repository.one(id);
        if(author != null){
            repository.delete(author);
        }
    }

    /**
     * Page authors
     *
     * @param page Page
     * @return Page with authors
     */
    public ResponsePage<MappedBean> getPage(Page page){
        page.setTotal(repository.pageCount(page.getSize()));
        List<MappedBean> items = null;
        if (page.getCurrent() > 0 && page.getCurrent() <= page.getTotal()) {
            items = repository.page(page);
        }
        return new ResponsePage<MappedBean>(items, page);
    }

    /**
     * Returns total authors count
     *
     * @return Author count
     */
    public Long getCount(){
        return repository.count();
    }

}
