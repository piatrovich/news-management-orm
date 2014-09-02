package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.News;
import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.CRUDRepository;
import com.epam.lab.news.data.repo.PagingAndSortingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        if (page.getCurrent() > 0 && page.getCurrent() <= page.getTotal()) {
            newses = repository.page(page);
        }
        return new ResponsePage<News>(newses, page);
    }

    public void addTag(Long id, Tag tag){
        News news = repository.one(id);
        if (news != null) {
            news.getTags().add(tag);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(news));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        repository.save(news);
    }

}
