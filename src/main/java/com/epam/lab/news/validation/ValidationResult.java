package com.epam.lab.news.validation;

import java.util.Map;

/**
 * Defines object for response to client
 *
 * @author Dzmitry Piatrovich
 */
public class ValidationResult {
    /** Keeps status of request */
    private boolean status;

    /** Keeps errors if request can not be execute */
    private Map<String, String> result;

    /**
     * Constructor
     *
     * @param status
     */
    public ValidationResult(boolean status){
        this.status = status;
    }

    /**
     * Getter for status
     *
     * @return True if request is right
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Setter for status
     *
     * @param status Request status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Getter for errors
     *
     * @return Errors for incorrect request
     */
    public Map<String, String> getResult() {
        return result;
    }

    /**
     * Setter for errors
     *
     * @param result Errors
     */
    public void setResult(Map<String, String> result) {
        this.result = result;
    }

}
