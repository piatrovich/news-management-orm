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

@SuppressWarnings("unchecked")
public class CommentRepository extends BasePagingRepositoryImpl {
    @Autowired
    SessionFactory sessionFactory;

    public CommentRepository(MappedBean bean){
        super(bean);
    }

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

    //@Override
    public Long getCountByNewsId(Long newsId) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long) session.createCriteria(Comment.class)
                .add(Restrictions.eq("news.id", newsId))
                .setProjection(Projections.rowCount())
                .uniqueResult();
        return count;
    }

    @Override
    public Long count() {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.createCriteria(Comment.class)
                .setProjection(Projections
                        .rowCount())
                .uniqueResult();
    }

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

    @Override
    public Long pageCount(Long pageSize, Long...params) {
        Long count = 0L;
        if (pageSize != 0L) {
            count = params.length != 0 ? getCountByNewsId(params[0]) : count();
            count = count % pageSize > 0 ? count / pageSize + 1: count / pageSize;
        }
        return count;
    }

}
