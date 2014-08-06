package com.epam.lab.news.database.jdbc.dao;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.database.jdbc.dao.constants.NewsConstants;
import com.epam.lab.news.exception.bean.ArticleNotFoundException;
import com.epam.lab.news.exception.bean.DAOException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for articles manipulation
 *
 * @author Dzmitry Piatrovich
 */
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class NewsDAO extends AbstractDAO {

    /**
     * Returns article
     *
     * @param id Unique article ID
     * @return Article object
     */
    public Article get(Long id) throws DAOException{
        Article article = new Article();
        Connection connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(NewsConstants.SQL_GET_NEWS_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                article.setId(resultSet.getLong(1));
                article.setTitle(resultSet.getString(2));
                article.setDescription(resultSet.getString(3));
                article.setText(resultSet.getString(4));
                article.setDate(resultSet.getDate(5));
            } else {
                throw new ArticleNotFoundException();
            }
        } catch (SQLException e) {
            logger.error(env.getProperty("error.dao.get.article") + id, e);
            throw new DAOException(NewsConstants.MSG_ERROR_FIND_ARTICLE + id, e);
        } finally {
            pool.returnConnection(connection);
        }
        return article;
    }

    /**
     * Returns list of all existing articles
     *
     * @return List of articles
     */
    public Iterable<Article> getAll() throws DAOException{
        List<Article> articles = new ArrayList<Article>();
        Connection connection = pool.getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(NewsConstants.SQL_GET_ALL_NEWS);
            while (resultSet.next()){
                Article article = new Article();
                article.setId(resultSet.getLong(1));
                article.setTitle(resultSet.getString(2));
                article.setDescription(resultSet.getString(3));
                article.setText(resultSet.getString(4));
                article.setDate(resultSet.getDate(5));
                articles.add(article);
            }
        } catch (SQLException e){
            logger.error(env.getProperty("error.dao.get.all.articles"), e);
            throw new DAOException(NewsConstants.MSG_ERROR_FIND_ALL_ARTICLES, e);
        } finally {
            pool.returnConnection(connection);
        }
        return articles;
    }

    /**
     * Saves proposed article
     *
     * @param article Article object
     */
    public void save(Article article) throws DAOException{
        Connection connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(NewsConstants.SQL_SAVE_NEWS);
            preparedStatement.setLong(1, article.getId());
            preparedStatement.setString(2, article.getClass().getName());
            preparedStatement.setString(3, article.getTitle());
            preparedStatement.setString(4, article.getDescription());
            preparedStatement.setString(5, article.getText());
            preparedStatement.setDate(6, new Date(article.getDate().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(env.getProperty("error.dao.save.article"), e);
            throw new DAOException(NewsConstants.MSG_ERROR_CREATE_ARTICLE + article.toString(), e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    /**
     * Updates existing article by proposed article
     *
     * @param article Article object
     */
    public void update(Article article) throws DAOException{
        Connection connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(NewsConstants.SQL_UPDATE_NEWS);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setString(3, article.getText());
            preparedStatement.setDate(4, new Date(article.getDate().getTime()));
            preparedStatement.setLong(5, article.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(env.getProperty("error.dao.update.article"), e);
            throw new DAOException(NewsConstants.MSG_ERROR_UPDATE_ARTICLE + article.toString(), e);
        } finally {
            pool.returnConnection(connection);
        }
    }

    /**
     * Deletes existing article ID
     *
     * @param id Unique article ID
     */
    public void delete(Long id) throws DAOException{
        Connection connection = pool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(NewsConstants.SQL_DELETE_NEWS);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error(env.getProperty("error.dao.delete.article"), e);
            throw new DAOException(NewsConstants.MSG_ERROR_DELETE_ARTICLE + id, e);
        } finally {
            pool.returnConnection(connection);
        }
    }

}
