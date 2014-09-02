package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.News;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.repo.CRUDRepository;
import com.epam.lab.news.data.repo.PagingAndSortingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("newsRepository")
@SuppressWarnings("unchecked")
public class NewsRepository implements PagingAndSortingRepository<News> {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<News> all() {
        Session session = sessionFactory.openSession();
        try {
            return session.createCriteria(News.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public News one(Long id) {
        Session session = sessionFactory.openSession();
        News tag =  (News) session.get(News.class, id);
        session.close();
        return tag;
    }

    @Override
    public News save(News news) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(news);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(news));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return news;
    }

    @Override
    public Long count() {
        Session session = sessionFactory.openSession();
        Long count = (Long) session.createCriteria(News.class)
                .setProjection(Projections
                        .rowCount())
                .uniqueResult();
        session.close();
        return count;
    }

    @Override
    public void delete(News entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean exists(Long id) {
        News news = one(id);
        return news != null;
    }

    /*@Override
    public List<News> firstPage(Long size) {
        Session session = sessionFactory.openSession();
        List<News> newses = session.createCriteria(News.class).setMaxResults(size.intValue()).list();
        session.close();
        return newses;
    }*/

    @Override
    public List<News> page(Page page, Long...params) {
        Session session = sessionFactory.openSession();
        List<News> newses = (List<News>)session.createCriteria(News.class)
                //.setFirstResult((int)((page.getCurrent() - 1) * page.getSize()))
                //.setMaxResults(page.getSize().intValue())
                .list();
        session.close();
        return newses;
    }

    @Override
    public Long pageCount(Long pageSize, Long...params) {
        if (pageSize > 0L){
            Long count = count();
            if (count % pageSize > 0){
                return count / pageSize + 1;
            } else {
                return count / pageSize;
            }
        } else {
            return 0L;
        }
    }

}
