package com.epam.lab.news.exception.bean;

/**
 * Defines exception of DAO layer
 *
 * @author Dzmitry Piatrovich
 */
public class RepositoryException extends ServiceException {

    /**
     * Constructor
     *
     * @param message Message from DAO layer
     * @param cause Bottom layer exception
     */
    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
