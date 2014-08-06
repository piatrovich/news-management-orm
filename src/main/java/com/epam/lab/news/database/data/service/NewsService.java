package com.epam.lab.news.database.data.service;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.data.bean.Counter;
import com.epam.lab.news.database.data.repo.CounterRepository;
import com.epam.lab.news.database.data.repo.NewsRepository;
import com.epam.lab.news.database.service.INewsService;
import com.epam.lab.news.database.service.ServiceConstants;
import com.epam.lab.news.exception.bean.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Implementation of logic layer using Spring Data
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0.alpha
 */
@Service("newsService")
public class NewsService implements INewsService {
    /** Wiring service with repository for working with Article instances */
    @Autowired
    private NewsRepository repository;

    /** Wiring service with repository for working with Counter documents */
    @Autowired
    private CounterRepository counterRepository;

    /**
     * Returns collection of all articles
     *
     * @return Collection of articles
     */
    public Iterable<Article> getAll() throws ServiceException{
        return repository.findAll();
    }

    /**
     * Returns a single article
     *
     * @param id Article id
     * @return Article instance
     */
    public Article get(Long id) throws ServiceException{
        return repository.findOne(id);
    }

    /**
     * Saves article
     *
     * @param article Article object
     */
    public void save(Article article) throws ServiceException{
        Counter counter = counterRepository.findOne(ServiceConstants.COUNTER_NAME_OF_ARTICLE);
        article.setId(counter.getNextId());
        article.setDate(new Date());
        repository.save(article);
        counterRepository.save(counter);
    }

    /**
     * Updates article
     *
     * @param article Article object
     */
    public void update(Article article) throws ServiceException{
        article.setDate(new Date());
        repository.save(article);
    }

    /**
     * Deletes article if it exist
     *
     * @param id Article id
     */
    public void delete(Long id) throws ServiceException{
        repository.delete(id);
    }

}
