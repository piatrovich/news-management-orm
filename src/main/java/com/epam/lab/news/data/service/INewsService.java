package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.News;
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
    public Iterable<News> getAll() throws ServiceException;

    /**
     * Returns requested articles
     *
     * @param id Article ID
     * @return Article object
     * @throws ServiceException if work can not be performed
     */
    public News get(Long id) throws ServiceException;

    /**
     * Saves new article
     *
     * @param news Article object
     * @throws ServiceException if work can not be performed
     */
    public void save(News news) throws ServiceException;

    /**
     * Updates existing articles
     *
     * @param news Article object
     * @throws ServiceException if work can not be performed
     */
    public void update(News news) throws ServiceException;

    /**
     * Deletes existing articles
     *
     * @param id Article ID
     * @throws ServiceException if work can not be performed
     */
    public void delete(Long id) throws ServiceException;

}
