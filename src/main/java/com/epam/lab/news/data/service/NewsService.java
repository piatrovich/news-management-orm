package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.News;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.CRUDRepository;
import com.epam.lab.news.data.repo.PagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class NewsService {
    @Autowired
    @Qualifier("newsRepository")
    PagingAndSortingRepository<News> repository;

    public List getAll(){
        return repository.all();
    }

    public News getSingle(Long id){
        return repository.one(id);
    }

    public Long getTotalNewsCount(){
        return repository.count();
    }

    public ResponsePage<News> getPage(Page page){
        page.setTotal(repository.pageCount(page.getSize()));
        List<News> newses = null;
        if (page.getCurrent() == 1) {
            newses = repository.firstPage(page.getSize());
        } else if (page.getCurrent() > 1 && page.getCurrent() <= page.getTotal()){
            newses = repository.page(page);
        }
        return new ResponsePage<News>(newses, page);
    }

}
