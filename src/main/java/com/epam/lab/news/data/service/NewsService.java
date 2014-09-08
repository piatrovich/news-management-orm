package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.Author;
import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.News;
import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.impl.NewsRepository;
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

    public MappedBean updateNews(News news){
        News old = (News) repository.one(news.getId());
        if (old != null){
            old.setTitle(news.getTitle());
            old.setShortText(news.getShortText());
            old.setFullText(news.getFullText());
            news.setModificationDate(new Date());
            return repository.save(old);
        }
        return news;
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

    public void addAuthor(Long id, Author author){
        News news = (News)repository.one(id);
        if (news != null) {
            news.getAuthors().add(author);
        }
        repository.save(news);
    }

    public void deleteNews(Long id){
        MappedBean news = (News) repository.one(id);
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
