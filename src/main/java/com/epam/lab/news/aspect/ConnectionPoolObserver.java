package com.epam.lab.news.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Observes for custom pool activity
 *
 * @author Dzmitry Piatrovich
 */
@Aspect
@PropertySource("classpath:logger.properties")
public class ConnectionPoolObserver {
    /** Pool activity logger */
    private static Logger logger = Logger.getLogger("pool");

    /**
     * Provides access to property sources
     */
    @Autowired
    private Environment environment;

    /**
     * Watching pool size
     *
     * @param size Number of connections
     */
    @AfterReturning(pointcut = "execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.size())",
                    returning = "size")
    public void checkPoolSize(Integer size){
        logger.info(environment.getProperty("info.pool.size") + size);
    }

    /**
     * Watching taking connections from pool
     */
    @After("execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.getConnection())")
    public void handleTakingConnection(){
        logger.info(environment.getProperty("info.pool.taken.connection"));
    }

    /**
     * Watching putting connections to pool
     */
    @After("execution(* com.epam.lab.news.database.jdbc.pool.ConnectionPool.returnConnection(..))")
    public void handleReturningConnection(){
        logger.info(environment.getProperty("info.pool.returned.connection"));
    }

}
