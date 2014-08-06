package com.epam.lab.news.database.jdbc.dao;

import com.epam.lab.news.database.data.bean.Counter;
import com.epam.lab.news.database.jdbc.dao.constants.CounterConstants;
import com.epam.lab.news.exception.bean.DAOException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Defines DAO layer - repository for working with counters
 *
 * @author Dzmitry Piatrovich
 */
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CounterDAO extends AbstractDAO {

    /**
     * Returns counter which contains highest article _id
     *
     * @return Counter object
     */
    public Counter get() throws DAOException{
        Counter counter = new Counter();
        Connection connection = pool.getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(CounterConstants.SQL_GET_ARTICLE_COUNTER);
            if(resultSet.next()){
                counter.setId(resultSet.getString(1));
                counter.setCount(resultSet.getLong(2));
            }
        } catch (SQLException e) {
            logger.error(env.getProperty("error.dao.get.counter"), e);
            throw new DAOException(CounterConstants.MSG_GET_ARTICLE_COUNTER, e);
        } finally {
            pool.returnConnection(connection);
        }
        return counter;
    }

    /**
     * Updates counter
     *
     * @param counter Counter object
     */
    public void update(Counter counter) throws DAOException{
        Connection connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(CounterConstants.SQL_UPDATE_ARTICLE_COUNTER);
            preparedStatement.setLong(1, counter.getCount());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(env.getProperty("error.dao.update.counter"), e);
            throw new DAOException(CounterConstants.MSG_UPDATE_ARTICLE_COUNTER, e);
        } finally {
            pool.returnConnection(connection);
        }
    }

}
