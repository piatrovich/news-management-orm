package com.epam.lab.news.database.jdbc.pool;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
@PropertySources({@PropertySource("classpath:pool/jdbc.properties"),
        @PropertySource("classpath:logger.properties")})
public class ConnectionPool {
    /**
     * Logger for errors
     */
    private static Logger logger = Logger.getLogger("errors");

    /**
     * Wiring configurer for initializing fields
     */
    @Autowired
    PropertySourcesPlaceholderConfigurer configurer;

    /**
     * Wiring environment for access to error messages
     */
    @Autowired
    Environment environment;

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.size}")
    private Integer size;

    /**
     * Keeps concurrent queue
     */
    private BlockingQueue<Connection> pool;
    private List<Connection> connections;

    /**
     * Initializes connection pool
     */
    @PostConstruct
    private void init() {
        pool = new ArrayBlockingQueue<Connection>(size);
        connections = new ArrayList<Connection>();
        try {
            Class.forName(driver);
            for (int i = 0; i < size; ++i) {
                Connection connection = DriverManager.getConnection(url);
                pool.add(connection);
                connections.add(connection);
            }
        } catch (ClassNotFoundException e) {
            logger.error(environment.getProperty("error.pool.class.not.found"), e);
        } catch (SQLException e) {
            logger.error(environment.getProperty("error.pool.create.connection"), e);
        }

    }

    /**
     * Returns database connection
     *
     * @return Connection object
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = pool.take();
            connections.remove(connection);
        } catch (InterruptedException e) {
            logger.error(environment.getProperty("error.pool.take.connection"), e);
        }
        int size = size();
        return connection;
    }

    /**
     * Return connection to pool
     *
     * @param connection Used connection
     */
    public void returnConnection(Connection connection) {
        try {
            if (connection != null) {
                pool.put(connection);
            } else {
                connection = DriverManager.getConnection(url);
                pool.put(connection);
            }
            connections.add(connection);
        } catch (InterruptedException e) {
            logger.error(environment.getProperty("error.pool.return.connection"), e);
        } catch (SQLException e) {
            logger.error(environment.getProperty("error.pool.create.connection"), e);
        }
    }

    /**
     * Closes all connections in pool
     */
    @PreDestroy
    private void destroy() {
        pool.clear();
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(environment.getProperty("error.pool.close.connection"), e);
            }
        }
    }

    /**
     * Helpful method for checking number of connections
     *
     * @return Number of connections
     */
    public int size() {
        return pool != null ? pool.size() : 0;
    }

}
