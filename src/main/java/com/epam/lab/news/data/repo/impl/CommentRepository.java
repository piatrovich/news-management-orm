package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.Comment;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.repo.ICommentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentRepository")
@SuppressWarnings("unchecked")
public class CommentRepository implements ICommentRepository {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Comment> all() {
        Session session = sessionFactory.openSession();
        List<Comment> comments = session.createCriteria(Comment.class).list();
        session.close();
        return comments;
    }

    public List<Comment> findByNewsId(Long id){
        Session session = sessionFactory.openSession();
        List<Comment> comments = session.createCriteria(Comment.class)
                .add(Restrictions.eq("news_news_id", id)).list();
        session.close();
        return comments;
    }

    @Override
    public Long getCountByNewsId(Long newsId) {
        Session session = sessionFactory.openSession();
        Long count = (Long) session.createCriteria(Comment.class)
                .add(Restrictions.eq("news.id", newsId))
                .setProjection(Projections.rowCount())
                .uniqueResult();
        session.close();
        return count;
    }

    @Override
    public Comment one(Long id) {
        return null;
    }

    @Override
    public Comment save(Comment entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entity);
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
        Long count = (Long) session.createCriteria(Comment.class)
                .setProjection(Projections
                        .rowCount())
                .uniqueResult();
        session.close();
        return count;
    }

    @Override
    public void delete(Comment entity) {

    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public List<Comment> page(Page page, Long...params) {
        Session session = sessionFactory.openSession();
        List<Comment> comments = null;
        if(params.length < 1) {
            comments = session.createCriteria(Comment.class)
                    .setFirstResult((int) ((page.getCurrent() - 1) * page.getSize()))
                    .setMaxResults(page.getSize().intValue()).list();
        } else {
            comments = session.createCriteria(Comment.class)
                    .add(Restrictions.eq("news.id", params[0]))
                    .setFirstResult((int) ((page.getCurrent() - 1) * page.getSize()))
                    .setMaxResults(page.getSize().intValue()).list();
        }
        session.close();
        return comments;
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
