package com.epam.lab.news.exception.bean;

/**
 * Defines exception for controller layer
 *
 * @author Dmitry Petrovich
 */
public class APIException extends InfrastructureException {

    /**
     * Constructor
     */
    public APIException(String message, Throwable cause) {
        super(message, cause);
    }
}
