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

@SuppressWarnings("unchecked")
public class NewsRepository extends BasePagingRepositoryImpl implements INewsRepository {

    public NewsRepository(MappedBean bean){
        super(new News());
    }

    @Override
    public List<MappedBean> mostCommented(Long count) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(RepositoryConstants.MOST_COMMENTED)
                .addEntity(bean.getClass());
        return query.list();
    }

    @Override
    public Integer countByTag(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(RepositoryConstants.COUNT_BY_TAG)
                .setParameter("id", id);
        return ((BigDecimal)query.list().get(0)).intValue();
    }

    @Override
    public List<MappedBean> newsByTag(Long id) {
        Session session = sessionFactory.getCurrentSession();
        List<MappedBean> newses = session.createSQLQuery(RepositoryConstants.NEWS_BY_TAG)
                .addEntity(bean.getClass())
                .setParameter("id", id)
                .list();
        return newses;
    }

    @Override
    public Integer countByAuthor(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(RepositoryConstants.COUNT_BY_AUTHOR).setParameter("id", id);
        Integer counter = ((BigDecimal)query.list().get(0)).intValue();
        return counter;
    }

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
