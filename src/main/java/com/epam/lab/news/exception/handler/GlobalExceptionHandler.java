package com.epam.lab.news.exception.handler;

import com.epam.lab.news.exception.bean.ArticleNotFoundException;
import com.epam.lab.news.exception.bean.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class handles runtime exceptions
 *
 * @author Dzmitry Piatrovich
 * @since 0.1.0-alpha
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles controller exceptions
     *
     * @return 500 - Server error
     */
    @ExceptionHandler(value = ControllerException.class)
    public ResponseEntity<Object> handleControllerException() {
        return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles exception if article not found
     *
     * @return 404 - Not found
     */
    @ExceptionHandler(value = ArticleNotFoundException.class)
    public ResponseEntity<Object> handleArticleNotFound() {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

}
