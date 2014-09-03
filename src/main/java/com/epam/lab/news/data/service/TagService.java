package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.impl.BasePagingAndSortingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class TagService {
    @Autowired
    @Qualifier("TagRepository")
    BasePagingAndSortingRepositoryImpl repository;

    public List all(){
        return repository.all();
    }

    public Tag get(Long id){
        return (Tag) repository.one(id);
    }

    public Tag save(Tag tag){
        return (Tag) repository.save(tag);
    }

    public void delete(Long id){
        Tag tag = (Tag) repository.one(id);
        if(tag != null){
            repository.delete(tag);
        }
    }

    public ResponsePage<MappedBean> getPage(Page page){
        page.setTotal(repository.pageCount(page.getSize()));
        List<MappedBean> items = null;
        if (page.getCurrent() > 0 && page.getCurrent() <= page.getTotal()) {
            items = repository.page(page);
        }
        return new ResponsePage<MappedBean>(items, page);
    }

    public Long count(){
        return repository.count();
    }

    public Boolean exists(Long id){
        return repository.exists(id);
    }

}
