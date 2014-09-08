package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.repo.impl.constants.RepositoryConstants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Custom implementation of paging repository for authors
 */
@SuppressWarnings("unchecked")
public class AuthorRepository extends BasePagingRepositoryImpl {
    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * Constructor
     *
     * @param bean Initializing by bean
     */
    public AuthorRepository(MappedBean bean){
        super(bean);
    }

    /**
     * Custom implementation
     *
     * @param params If need to get author list for single news set a news ID
     * @return List of authors
     */
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

    /**
     * Checks tag existing
     *
     * @param name Tag name
     * @return True if exists, otherwise false
     */
    public boolean existByName(String name){
        Session session = sessionFactory.getCurrentSession();
        List<MappedBean> entities = session.createCriteria(bean.getClass())
                .add(Restrictions.eq("name", name)).list();
        return entities.size() > 0;
    }
}
