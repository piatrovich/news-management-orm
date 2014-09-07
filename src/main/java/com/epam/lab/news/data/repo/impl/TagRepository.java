package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.repo.impl.constants.RepositoryConstants;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SuppressWarnings("unchecked")
public class TagRepository extends BasePagingRepositoryImpl {
    @Autowired
    protected SessionFactory sessionFactory;

    public TagRepository(MappedBean bean){
        super(bean);
    }

    @Override
    public List<MappedBean> all(Long...params) {
        if (params.length > 0 && params[0] != null) {
            Session session = sessionFactory.getCurrentSession();
            List<MappedBean> tags = session.createSQLQuery(RepositoryConstants.TAGS_BY_NEWS_ID)
                    .addEntity(bean.getClass())
                    .setParameter("id", params[0]).list();
            return tags;
        } else {
            return super.all(params);
        }
    }

}
