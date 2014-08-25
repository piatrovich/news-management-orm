package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.Comment;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.repo.PagingAndSortingRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentRepository")
@SuppressWarnings("unchecked")
public class CommentRepository implements PagingAndSortingRepository<Comment> {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Comment> all() {
        Session session = sessionFactory.openSession();
        try {
            return session.createCriteria(Comment.class).list();
        } finally {
            session.close();
        }
    }

    @Override
    public Comment one(Long id) {
        return null;
    }

    @Override
    public void save(Comment entity) {

    }

    @Override
    public Long count() {
        return null;
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
