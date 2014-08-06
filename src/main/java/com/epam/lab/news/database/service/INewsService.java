package com.epam.lab.news.database.service;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.exception.bean.ServiceException;

/**
 * Describes a logical layer of application
 *
 * @author Dzmitry Piatrovich
 */
public interface INewsService {

    /**
     * Returns all articles
     *
     * @return All articles
     * @throws ServiceException if work can not be performed
     */
    public Iterable<Article> getAll() throws ServiceException;

    /**
     * Returns requested articles
     *
     * @param id Article ID
     * @return Article object
     * @throws ServiceException if work can not be performed
     */
    public Article get(Long id) throws ServiceException;

    /**
     * Saves new article
     *
     * @param article Article object
     * @throws ServiceException if work can not be performed
     */
    public void save(Article article) throws ServiceException;

    /**
     * Updates existing articles
     *
     * @param article Article object
     * @throws ServiceException if work can not be performed
     */
    public void update(Article article) throws ServiceException;

    /**
     * Deletes existing articles
     *
     * @param id Article ID
     * @throws ServiceException if work can not be performed
     */
    public void delete(Long id) throws ServiceException;

}
