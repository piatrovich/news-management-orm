package com.epam.lab.news.database.jdbc.dao.service;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.data.bean.Counter;
import com.epam.lab.news.database.jdbc.dao.CounterDAO;
import com.epam.lab.news.database.jdbc.dao.NewsDAO;
import com.epam.lab.news.database.service.INewsService;
import com.epam.lab.news.database.service.ServiceConstants;
import com.epam.lab.news.exception.bean.DAOException;
import com.epam.lab.news.exception.bean.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service implementation using custom repositories
 *
 * @author Dzmitry Piatrovich
 */
@Service("daoService")
public class DAOService implements INewsService {

    /** Wiring custom news repository */
    @Autowired
    private NewsDAO newsRepository;

    /** Wiring custom counter repository */
    @Autowired
    private CounterDAO counterRepository;

    /**
     * Implements find all articles action using custom repositories
     *
     * @return List of all articles
     * @throws ServiceException
     */
    public Iterable<Article> getAll() throws ServiceException {
        try {
            return newsRepository.getAll();
        } catch (DAOException e) {
            throw new ServiceException(ServiceConstants.MSG_FIND_ALL_ACTION, e);
        }
    }

    /**
     * Implements find single article action using custom repositories
     *
     * @param id Article ID
     * @return Article object
     * @throws ServiceException
     */
    public Article get(Long id) throws ServiceException{
        try {
            return newsRepository.get(id);
        } catch (DAOException e) {
            throw new ServiceException(ServiceConstants.MSG_FIND_SINGLE_ACTION, e);
        }
    }

    /**
     * Implements save action using custom repositories
     *
     * @param article Article object
     * @throws ServiceException
     */
    public void save(Article article) throws ServiceException{
        try {
            Counter counter = counterRepository.get();
            article.setId(counter.getNextId());
            counterRepository.update(counter);
            article.setDate(new Date());
            newsRepository.save(article);
        } catch (DAOException e) {
            throw new ServiceException(ServiceConstants.MSG_CREATE_ACTION, e);
        }

    }

    /**
     * Implements update action using custom repositories
     *
     * @param article Article object
     * @throws ServiceException
     */
    public void update(Article article) throws ServiceException{
        article.setDate(new Date());
        try {
            newsRepository.update(article);
        } catch (DAOException e) {
            throw new ServiceException(ServiceConstants.MSG_UPDATE_ACTION, e);
        }
    }

    /**
     * Implements delete action using custom repositories
     *
     * @param id Article ID
     * @throws ServiceException
     */
    public void delete(Long id) throws ServiceException{
        try {
            newsRepository.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(ServiceConstants.MSG_DELETE_ACTION, e);
        }
    }

}
