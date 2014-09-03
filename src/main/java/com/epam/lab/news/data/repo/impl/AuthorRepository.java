package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.repo.PagingAndSortingRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("authorRepository")
@SuppressWarnings("unchecked")
public class AuthorRepository implements PagingAndSortingRepository<Author> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Author> page(Page page, Long... params) {
        return null;
    }

    @Override
    public Long pageCount(Long pageSize, Long... params) {
        return null;
    }

    @Override
    public List<Author> all() {
        Session session = sessionFactory.openSession();
        try {
            return session.createCriteria(Author.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public Author one(Long id) {
        Session session = sessionFactory.openSession();
        Author author =  (Author) session.get(Author.class, id);
        session.close();
        return author;
    }

    @Override
    public Author save(Author entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public Long count() {
        Session session = sessionFactory.openSession();
        Long count = (Long) session.createCriteria(Author.class)
                .setProjection(Projections
                        .rowCount())
                .uniqueResult();
        session.close();
        return count;
    }

    @Override
    public void delete(Author entity) {
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
        Author author = one(id);
        return author != null;
    }

}
