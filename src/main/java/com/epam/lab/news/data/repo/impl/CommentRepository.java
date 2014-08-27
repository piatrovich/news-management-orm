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
                .add(Restrictions.eq("news.id", id)).list();
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
    public void save(Comment entity) {
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
    public List<Comment> firstPage(Long size) {
        return null;
    }

    @Override
    public List<Comment> page(Page page) {
        return null;
    }

    @Override
    public Long pageCount(Long pageSize) {
        return null;
    }
}
