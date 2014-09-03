package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.News;
import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.impl.BasePagingAndSortingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    @Qualifier("NewsRepository")
    BasePagingAndSortingRepositoryImpl repository;

    public List getAll(){
        return repository.all();
    }

    public News getSingle(Long id){
        return (News) repository.one(id);
    }

    public Long getTotalNewsCount(){
        return repository.count();
    }

    public ResponsePage<MappedBean> getPage(Page page){
        page.setTotal(repository.pageCount(page.getSize()));
        List<MappedBean> newses = null;
        if (page.getCurrent() > 0 && page.getCurrent() <= page.getTotal()) {
            newses = repository.page(page);
        }
        return new ResponsePage<MappedBean>(newses, page);
    }

    public void addTag(Long id, Tag tag){
        News news = (News) repository.one(id);
        if (news != null) {
            news.getTags().add(tag);
        }
        repository.save(news);
    }

    public void deleteNews(Long id){
        News news = (News) repository.one(id);
        if (news != null) {
            repository.delete(news);
        }
    }

}
