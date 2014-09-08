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

/**
 * News service layer
 *
 * @author Dzmitry Piatrovich
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NewsService {
    @Autowired
    @Qualifier("NewsRepository")
    NewsRepository repository;

    /**
     * Returns news list
     *
     * @return News list
     */
    public List getAll(){
        return repository.all();
    }

    /**
     * Returns one news
     *
     * @param id News id
     * @return News
     */
    public News getSingle(Long id){
        return (News) repository.one(id);
    }

    /**
     * Saves news
     *
     * @param news News
     * @return Saved news
     */
    public MappedBean saveNews(News news){
        news.setCreationDate(new Date());
        return repository.save(news);
    }

    /**
     * Updates news
     *
     * @param news News
     * @return Saved news
     */
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

    /**
     * Returns total news count
     *
     * @return News count
     */
    public Long getTotalNewsCount(){
        return repository.count();
    }

    /**
     * Returns news page
     *
     * @param page Page
     * @return News page with page options
     */
    public ResponsePage<MappedBean> getPage(Page page){
        page.setTotal(repository.pageCount(page.getSize()));
        List<MappedBean> newses = null;
        if (page.getCurrent() > 0 && page.getCurrent() <= page.getTotal()) {
            newses = repository.page(page);
        }
        return new ResponsePage<MappedBean>(newses, page);
    }

    /**
     * Adds tag to news
     *
     * @param id News id
     * @param tag Tag
     */
    public void addTag(Long id, Tag tag){
        News news = (News) repository.one(id);
        if (news != null) {
            news.getTags().add(tag);
        }
        repository.save(news);
    }

    /**
     * Adds author to news
     * @param id News id
     * @param author Author
     */
    public void addAuthor(Long id, Author author){
        News news = (News)repository.one(id);
        if (news != null) {
            news.getAuthors().add(author);
        }
        repository.save(news);
    }

    /**
     * Deletes news
     *
     * @param id News id
     */
    public void deleteNews(Long id){
        MappedBean news = (News) repository.one(id);
        if (news != null) {
            repository.delete(news);
        }
    }

    /**
     * Returns list of most commented news
     *
     * @param count List size
     * @return News list
     */
    public List getMostCommented(Long count){
        return repository.mostCommented(count);
    }

    /**
     * Returns news count by tag
     *
     * @param id Tag id
     * @return News count
     */
    public Integer getCountByTag(Long id){
        return repository.countByTag(id);
    }

    /**
     * Returns news count by author
     *
     * @param id Author id
     * @return News list
     */
    public List<MappedBean> newsByTag(Long id){
        return repository.newsByTag(id);
    }

    /**
     * Returns news count by author
     *
     * @param id Author id
     * @return News count
     */
    public Integer getCountByAuthor(Long id){
        return repository.countByAuthor(id);
    }

    /**
     * Returns news list by author
     *
     * @param id Author id
     * @return News list
     */
    public List<MappedBean> newsByAuthor(Long id){
        return repository.newsByAuthor(id);
    }

}
