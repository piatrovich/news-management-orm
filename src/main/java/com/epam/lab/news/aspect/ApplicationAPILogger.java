package com.epam.lab.news.aspect;

import com.epam.lab.news.bean.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * This class observes json traffic.
 *
 * @author Dzmitry Piatrovich
 */
@Aspect
public class ApplicationAPILogger {
    /** Logger for observe json documents */
    private static Logger apiLogger = Logger.getLogger("api");

    /** Logger for errors */
    private static Logger errorLogger = Logger.getLogger("errors");

    /**
     * Logging articles which requested for view
     *
     * @param article
     */
    @AfterReturning(pointcut = "execution(* com.epam.lab.news.controller.APIController.getArticleForView(..))",
                    returning = "article")
    public void listenReturnedPage(Article article) {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(format);
        try {
            apiLogger.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(article));
        } catch (JsonProcessingException e) {
            errorLogger.error(e);
        }
    }

}
