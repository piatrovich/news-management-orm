package com.epam.lab.news.database.jdbc.dao;

import com.epam.lab.news.database.jdbc.pool.ConnectionPool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.sql.*;

/**
 * Defines abstract class for DAO classes.<br />
 * Provides access to connection pool and dao logger.
 *
 * @author Dzmitry Piatrovich
 */
@PropertySource("classpath:logger.properties")
public abstract class AbstractDAO {
    /** Logger for dao layer */
    protected static Logger logger = Logger.getLogger("dao");

    /** Connection pool */
    @Autowired
    protected ConnectionPool pool;

    /**
     * Provides access to property sources
     */
    @Autowired
    protected Environment env;

    /** Keeps statement object */
    protected Statement statement;

    /** Keeps result set object */
    protected ResultSet resultSet;

    /** Keeps prepared statement object */
    protected PreparedStatement preparedStatement;

    protected void close(){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(env.getProperty("error.dao.statement.close"), e);
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(env.getProperty("error.dao.prepared.statement.close"), e);
            }
        }
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(env.getProperty("error.dao.result.set.close"), e);
            }
        }
    }

}
