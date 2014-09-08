package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.Comment;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.bean.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Custom implementation of paging repository
 */
@SuppressWarnings("unchecked")
public class CommentRepository extends BasePagingRepositoryImpl {
    @Autowired
    SessionFactory sessionFactory;

    /**
     * Constructor
     *
     * @param bean Bean for initializing
     */
    public CommentRepository(MappedBean bean){
        super(bean);
    }

    /**
     * Returns comments for one news if param existing, otherwise calls base implementation
     *
     * @param params News id
     * @return All comments for one news
     */
    @Override
    public List<MappedBean> all(Long...params) {
        if (params.length > 0 && params[0]!= null){
            Session session = sessionFactory.getCurrentSession();
            return session.createCriteria(Comment.class)
                    .add(Restrictions.eq("news_news_id", params[0]))
                    .list();
        } else {
            return super.all(params);
        }
    }

    /**
     * Returns comments count for one news if param existing, otherwise calls base implementation
     *
     * @param params News id
     * @return Count
     */
    @Override
    public Long count(Long...params) {
        if (params.length > 0 && params[0]!= null){
            Session session = sessionFactory.getCurrentSession();
            return (Long) session.createCriteria(Comment.class)
                    .add(Restrictions.eq("news.id", params[0]))
                    .setProjection(Projections.rowCount())
                    .uniqueResult();
        } else {
            return super.count(params);
        }
    }

    /**
     * Returns comments on page for one news if param existing, otherwise calls base implementation
     *
     * @param page Page
     * @param params News id
     * @return List of pages
     */
    @Override
    public List<MappedBean> page(Page page, Long...params) {
        Session session = sessionFactory.getCurrentSession();
        if(params.length > 0 && params[0] != null)  {
            return session.createCriteria(Comment.class)
                    .add(Restrictions.eq("news.id", params[0]))
                    .setFirstResult((int) ((page.getCurrent() - 1) * page.getSize()))
                    .setMaxResults(page.getSize().intValue())
                    .list();
        } else {
            return super.page(page, params);
        }
    }

    /**
     * Returns number of comment pages for one news if news id transfered in param, <br />
     * otherwise calls base implementation
     *
     * @param pageSize Size of page
     * @param params News id
     * @return Number of pages
     */
    @Override
    public Long pageCount(Long pageSize, Long...params) {
        Long count = 0L;
        if (pageSize != 0L) {
            if (params.length > 0 && params[0] != null){
                count = count(params);
                count = count % pageSize > 0 ? count / pageSize + 1: count / pageSize;
            } else {
                return super.count(params);
            }
        }
        return count;
    }

}
