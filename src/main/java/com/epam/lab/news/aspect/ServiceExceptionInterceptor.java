package com.epam.lab.news.aspect;

import com.epam.lab.news.exception.bean.RepositoryException;
import com.epam.lab.news.exception.bean.ServiceException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ServiceExceptionInterceptor {

    @AfterThrowing(pointcut = "execution(* com.epam.lab.news.data.service..*(..))",
            throwing = "exception")
    public void handle(RepositoryException exception) throws ServiceException {
        throw new ServiceException("Service exception", exception);
    }

}
