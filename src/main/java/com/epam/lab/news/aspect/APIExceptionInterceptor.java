package com.epam.lab.news.aspect;

import com.epam.lab.news.exception.bean.APIException;
import com.epam.lab.news.exception.bean.ServiceException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class APIExceptionInterceptor {

    @AfterThrowing(pointcut = "execution(* com.epam.lab.news.data.service..*(..))",
            throwing = "exception")
    public void handle(ServiceException exception) throws APIException {
        throw new APIException("API exception", exception);
    }

}
