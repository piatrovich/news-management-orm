package com.epam.lab.news.aspect;

import com.epam.lab.news.exception.bean.RepositoryException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * Intercepts exceptions from repository layer and throws it up
 *
 * @author Dzmitry Piatrovich
 */
@Aspect
public class RepositoryExceptionInterceptor {

    /**
     * Intercepts any exceptions from api layer and throw up
     *
     * @param exception Any exception from repository layer
     * @throws RepositoryException
     */
    @AfterThrowing(pointcut = "execution(* com.epam.lab.news.data.repo.impl..*(..))",
                   throwing = "exception")
    public void handle(Throwable exception) throws RepositoryException{
        throw new RepositoryException("Repository exception", exception);
    }

}
