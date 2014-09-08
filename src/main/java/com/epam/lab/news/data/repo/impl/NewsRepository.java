package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.News;
import com.epam.lab.news.data.repo.INewsRepository;
import com.epam.lab.news.data.repo.impl.constants.RepositoryConstants;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of news repository
 */
@SuppressWarnings("unchecked")
public class NewsRepository extends BasePagingRepositoryImpl implements INewsRepository {

    /**
     * Constructor
     *
     * @param bean Any bean for initializing
     */
    public NewsRepository(MappedBean bean){
        super(new News());
    }

    /**
     * Return list of most commented news
     *
     * @param count List size
     * @return News list
     */
    @Override
    public List<MappedBean> mostCommented(Long count) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(RepositoryConstants.MOST_COMMENTED)
                .addEntity(bean.getClass());
        return query.list();
    }

    /**
     * Returns number of news with particular tag
     *
     * @param id Tag id
     * @return Number of news
     */
    @Override
    public Integer countByTag(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(RepositoryConstants.COUNT_BY_TAG)
                .setParameter("id", id);
        return ((BigDecimal)query.list().get(0)).intValue();
    }

    /**
     * Returns list of news with particular tag
     *
     * @param id Tag id
     * @return News list
     */
    @Override
    public List<MappedBean> newsByTag(Long id) {
        Session session = sessionFactory.getCurrentSession();
        List<MappedBean> newses = session.createSQLQuery(RepositoryConstants.NEWS_BY_TAG)
                .addEntity(bean.getClass())
                .setParameter("id", id)
                .list();
        return newses;
    }

    /**
     * Returns count of news with particular author
     *
     * @param id Author id
     * @return News count
     */
    @Override
    public Integer countByAuthor(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(RepositoryConstants.COUNT_BY_AUTHOR).setParameter("id", id);
        Integer counter = ((BigDecimal)query.list().get(0)).intValue();
        return counter;
    }

    /**
     * Returns list of news with particular author
     *
     * @param id Author id
     * @return News list
     */
    @Override
    public List<MappedBean> newsByAuthor(Long id) {
        Session session = sessionFactory.getCurrentSession();
        List<MappedBean> newses = session.createSQLQuery(RepositoryConstants.NEWS_BY_AUTHOR)
                .addEntity(bean.getClass())
                .setParameter("id", id)
                .list();
        return newses;
    }
}
