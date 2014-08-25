package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.repo.PagingAndSortingRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tagRepository")
@SuppressWarnings("unchecked")
public class TagRepository implements PagingAndSortingRepository<Tag> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List all(){
        Session session = sessionFactory.openSession();
        try {
            return session.createCriteria(Tag.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public Tag one(Long id) {
        Session session = sessionFactory.openSession();
        Tag tag =  (Tag) session.get(Tag.class, id);
        session.close();
        return tag;
    }

    @Override
    public void save(Tag tag) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(tag);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Long count() {
        Session session = sessionFactory.openSession();
        Long count = (Long) session.createCriteria(Tag.class)
                .setProjection(Projections
                        .rowCount())
                .uniqueResult();
        session.close();
        return count;
    }

    @Override
    public void delete(Tag entity) {
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
        Tag tag = one(id);
        return tag != null;
    }

    @Override
    public List<Tag> firstPage(Long size) {
        Session session = sessionFactory.openSession();
        List<Tag> tags = session.createCriteria(Tag.class).setMaxResults(size.intValue()).list();
        session.close();
        return tags;
    }

    @Override
    public Long pageCount(Long pageSize) {
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

    @Override
    public List<Tag> page(Page page) {
        Session session = sessionFactory.openSession();
        List<Tag> tags = session.createCriteria(Tag.class)
                .setFirstResult((int)((page.getCurrent() - 1) * page.getSize()))
                .setMaxResults(page.getSize().intValue()).list();
        session.close();
        return tags;
    }

}
