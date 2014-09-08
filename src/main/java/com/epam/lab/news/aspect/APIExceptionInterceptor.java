package com.epam.lab.news.aspect;

import com.epam.lab.news.exception.bean.APIException;
import com.epam.lab.news.exception.bean.ServiceException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * Intercepts exceptions from controller layer and throws it up
 *
 * @author Dzmitry Piatrovich
 */
@Aspect
public class APIExceptionInterceptor {

    /**
     * Intercepts any exceptions from api layer and throw up API exception for global handler
     *
     * @param exception Any exception from api layer
     * @throws APIException
     */
    @AfterThrowing(pointcut = "execution(* com.epam.lab.news.controller..*(..))",
            throwing = "exception")
    public void handle(ServiceException exception) throws APIException {
        throw new APIException("API exception", exception);
    }

}
