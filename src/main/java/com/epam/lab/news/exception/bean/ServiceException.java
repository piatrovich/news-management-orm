package com.epam.lab.news.exception.bean;

/**
 * Defines exceptions of service layer
 *
 * @author Dzmitry Piatrovich
 */
public class ServiceException extends InfrastructureException {

    /**
     * Constructor
     *
     * @param message Layer message
     * @param cause Exception from 'bottom' layer
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
