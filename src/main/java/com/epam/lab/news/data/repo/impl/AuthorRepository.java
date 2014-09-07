package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.repo.impl.constants.RepositoryConstants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SuppressWarnings("unchecked")
public class AuthorRepository extends BasePagingRepositoryImpl {
    @Autowired
    protected SessionFactory sessionFactory;

    public AuthorRepository(MappedBean bean){
        super(bean);
    }

    @Override
    public List<MappedBean> all(Long... params) {
        if (params.length > 0 && params[0] != null) {
            Session session = sessionFactory.getCurrentSession();
            List<MappedBean> tags = session.createSQLQuery(RepositoryConstants.AUTHORS_BY_NEWS_ID)
                    .addEntity(bean.getClass())
                    .setParameter("id", params[0]).list();
            return tags;
        } else {
            return super.all(params);
        }
    }
}
