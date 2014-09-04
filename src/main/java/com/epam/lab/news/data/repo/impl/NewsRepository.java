package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.News;
import com.epam.lab.news.data.repo.INewsRepository;
import com.epam.lab.news.data.repo.impl.constants.RepositoryConstants;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

@SuppressWarnings("unchecked")
public class NewsRepository extends BasePagingRepositoryImpl implements INewsRepository {

    public NewsRepository(MappedBean bean){
        super(new News());
    }

    @Override
    public List<MappedBean> mostCommented(Long count) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(RepositoryConstants.MOST_COMMENTED).addEntity(bean.getClass());
        return query.list();
    }

}
