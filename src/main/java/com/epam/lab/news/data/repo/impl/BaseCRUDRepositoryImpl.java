package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.repo.CRUDRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

/**
 * Base implementation for CRUD operations
 */
@SuppressWarnings("unchecked")
public class BaseCRUDRepositoryImpl implements CRUDRepository<MappedBean> {
    @Autowired
    protected SessionFactory sessionFactory;

    protected MappedBean bean;

    /**
     * Constructor
     *
     * @param bean Initialized by entity
     */
    public BaseCRUDRepositoryImpl(MappedBean bean){
        this.bean = bean;
    }

    /**
     * Base implementation return list of all entities
     *
     * @param params For custom implementations
     * @return List of entities
     */
    @Override
    public List<MappedBean> all(Long...params) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(bean.getClass()).list();
    }

    /**
     * Returns single entity
     *
     * @param id Entity ID
     * @return Entity
     */
    @Override
    public MappedBean one(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (MappedBean) session.get(bean.getClass(), id);
    }

    /**
     * Returns updated entity after saving
     *
     * @param entity Entity
     * @return Entity
     */
    @Override
    public MappedBean save(MappedBean entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    /**
     * Custom implementation return count of all entities
     *
     * @param params For custom implementations
     * @return Count
     */
    @Override
    public Long count(Long...params) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long) session.createCriteria(bean.getClass())
                .setProjection(Projections
                        .rowCount())
                .uniqueResult();
        return count;
    }

    /**
     * Deletes entity
     *
     * @param entity Application bean
     */
    @Override
    public void delete(MappedBean entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
    }

    /**
     * Checks entity existing
     *
     * @param id Entity ID
     * @return True if exists, otherwise false
     */
    @Override
    public boolean exists(Long id) {
        MappedBean entity = one(id);
        return entity != null;
    }

}
