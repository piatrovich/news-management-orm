package com.epam.lab.news.aspect;

import com.epam.lab.news.exception.bean.RepositoryException;
import com.epam.lab.news.exception.bean.ServiceException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * Intercepts exceptions from service layer and throws it up
 *
 * @author Dzmitry Piatrovich
 */
@Aspect
public class ServiceExceptionInterceptor {

    /**
     * Intercepts any exceptions from api layer and throw up
     *
     * @param exception Any exception from service level
     * @throws ServiceException
     */
    @AfterThrowing(pointcut = "execution(* com.epam.lab.news.data.service..*(..))",
            throwing = "exception")
    public void handle(RepositoryException exception) throws ServiceException {
        throw new ServiceException("Service exception", exception);
    }

}
