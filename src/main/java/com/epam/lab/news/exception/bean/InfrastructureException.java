package com.epam.lab.news.exception.bean;

/**
 * Defines abstract exception for application
 *
 * @author Dzmitry Piatrovich
 */
public abstract class InfrastructureException extends Exception {

    /**
     * Constructor
     *
     * @param message Exception message
     * @param cause Exception from bottom layer
     */
    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }

}
