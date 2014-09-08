package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.repo.PagingRepository;
import org.hibernate.Session;

import java.util.List;

/**
 * Base implementation of CRUD repository
 */
@SuppressWarnings("unchecked")
public class BasePagingRepositoryImpl extends BaseCRUDRepositoryImpl
        implements PagingRepository<MappedBean> {

    /**
     * Constructor
     *
     * @param bean Bean for initializing
     */
    public BasePagingRepositoryImpl(MappedBean bean){
        super(bean);
    }

    /**
     * Return list of items on page
     *
     * @param page Page
     * @param params For custom implementations
     * @return Entity list
     */
    @Override
    public List<MappedBean> page(Page page, Long... params) {
        Session session = sessionFactory.getCurrentSession();
        List<MappedBean> entities = session.createCriteria(bean.getClass())
                .setFirstResult((int)((page.getCurrent() - 1) * page.getSize()))
                .setMaxResults(page.getSize().intValue()).list();
        return entities;
    }

    /**
     * Number of all pages
     *
     * @param pageSize Size of page
     * @param params For custom implementations
     * @return Number of pages
     */
    @Override
    public Long pageCount(Long pageSize, Long... params) {
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
