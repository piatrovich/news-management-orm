package com.epam.lab.news.data.repo.impl;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.repo.PagingRepository;
import org.hibernate.Session;

import java.util.List;

@SuppressWarnings("unchecked")
public class BasePagingRepositoryImpl extends BaseCRUDRepositoryImpl
        implements PagingRepository<MappedBean> {

    public BasePagingRepositoryImpl(MappedBean bean){
        super(bean);
    }

    @Override
    public List<MappedBean> page(Page page, Long... params) {
        Session session = sessionFactory.openSession();
        List<MappedBean> entities = session.createCriteria(bean.getClass())
                .setFirstResult((int)((page.getCurrent() - 1) * page.getSize()))
                .setMaxResults(page.getSize().intValue()).list();
        session.close();
        return entities;
    }

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
