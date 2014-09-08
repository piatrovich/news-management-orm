package com.epam.lab.news.aspect;

import com.epam.lab.news.exception.bean.RepositoryException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class RepositoryExceptionInterceptor {

    @AfterThrowing(pointcut = "execution(* com.epam.lab.news.data.repo.impl..*(..))",
                   throwing = "exception")
    public void handle(Throwable exception) throws RepositoryException{
        throw new RepositoryException("Repository exception", exception);
    }

}
