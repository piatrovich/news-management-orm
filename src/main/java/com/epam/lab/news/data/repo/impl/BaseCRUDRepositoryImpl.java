package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.repo.CRUDRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SuppressWarnings("unchecked")
public class BaseCRUDRepositoryImpl implements CRUDRepository<MappedBean> {
    @Autowired
    SessionFactory sessionFactory;

    private MappedBean bean;

    public BaseCRUDRepositoryImpl(MappedBean bean){
        this.bean = bean;
    }

    @Override
    public List<MappedBean> all() {
        Session session = sessionFactory.openSession();
        try {
            return session.createCriteria(bean.getClass()).list();
        } finally {
            session.close();
        }
    }

    @Override
    public MappedBean one(Long id) {
        Session session = sessionFactory.openSession();
        MappedBean entity =  (MappedBean) session.get(bean.getClass(), id);
        session.close();
        return entity;
    }

    @Override
    public MappedBean save(MappedBean entity) {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void delete(MappedBean entity) {

    }

    @Override
    public boolean exists(Long id) {
        MappedBean entity = one(id);
        return entity != null;
    }

}
