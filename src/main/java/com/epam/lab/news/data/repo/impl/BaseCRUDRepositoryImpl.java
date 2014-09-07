package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.repo.CRUDRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SuppressWarnings("unchecked")
public class BaseCRUDRepositoryImpl implements CRUDRepository<MappedBean> {
    @Autowired
    protected SessionFactory sessionFactory;

    protected MappedBean bean;

    public BaseCRUDRepositoryImpl(MappedBean bean){
        this.bean = bean;
    }

    @Override
    public List<MappedBean> all(Long...params) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(bean.getClass()).list();
    }

    @Override
    public MappedBean one(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (MappedBean) session.get(bean.getClass(), id);
    }

    @Override
    public MappedBean save(MappedBean entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        }
        return entity;
    }

    @Override
    public Long count() {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long) session.createCriteria(bean.getClass())
                .setProjection(Projections
                        .rowCount())
                .uniqueResult();
        return count;
    }

    @Override
    public void delete(MappedBean entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public boolean exists(Long id) {
        MappedBean entity = one(id);
        return entity != null;
    }

}
