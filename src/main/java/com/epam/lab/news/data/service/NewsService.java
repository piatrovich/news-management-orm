package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.News;
import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.impl.NewsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NewsService {
    @Autowired
    @Qualifier("NewsRepository")
    NewsRepository repository;

    public List getAll(){
        return repository.all();
    }

    public News getSingle(Long id){
        return (News) repository.one(id);
    }

    public MappedBean saveNews(News news){
        news.setCreationDate(new Date());
        return repository.save(news);
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
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(page));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponsePage<MappedBean>(newses, page);
    }

    @Transactional(readOnly = false)
    public void addTag(Long id, Tag tag){
        News news = (News) repository.one(id);
        if (news != null) {
            Hibernate.initialize(news);
            Hibernate.initialize(news.getTags().add(tag));

            //news.getTags().add(tag);
        }
        repository.save(news);
    }

    public void addAuthor(Long id, Author author){
        News news = (News)repository.one(id);
        if (news != null) {
            news.getAuthors().add(author);
        }
        repository.save(news);
    }

    public void deleteNews(Long id){
        News news = (News) repository.one(id);
        if (news != null) {
            repository.delete(news);
        }
    }

    public List getMostCommented(Long count){
        return repository.mostCommented(count);
    }

    public Integer getCountByTag(Long id){
        return repository.countByTag(id);
    }

    public List<MappedBean> newsByTag(Long id){
        return repository.newsByTag(id);
    }

    public Integer getCountByAuthor(Long id){
        return repository.countByAuthor(id);
    }

    public List<MappedBean> newsByAuthor(Long id){
        return repository.newsByAuthor(id);
    }

}
