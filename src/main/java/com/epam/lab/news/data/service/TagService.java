package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.MappedBean;
import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.impl.BasePagingRepositoryImpl;
import com.epam.lab.news.data.repo.impl.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Tag service
 *
 * @author Dzmitry Piatrovich
 */
@Service
@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.REQUIRED)
public class TagService {
    @Autowired
    @Qualifier("TagRepository")
    TagRepository repository;

    /**
     * Returns list of tag
     *
     * @param params News id (Optionsl)
     * @return Tag list
     */
    public List all(Long...params){
        return repository.all(params);
    }

    /**
     * Return one tag
     *
     * @param id Tag id
     * @return Tag
     */
    public Tag get(Long id){
        return (Tag) repository.one(id);
    }

    /**
     * Saves tag
     *
     * @param tag Tag
     * @return Saved Tag
     */
    public Tag save(Tag tag){
        return (Tag) repository.save(tag);
    }

    /**
     * Deletes tag
     *
     * @param id Tag id
     */
    public void delete(Long id){
        Tag tag = (Tag) repository.one(id);
        if(tag != null){
            repository.delete(tag);
        }
    }

    /**
     * Returns tag page
     *
     * @param page Page
     * @return Tag page
     */
    public ResponsePage<MappedBean> getPage(Page page){
        page.setTotal(repository.pageCount(page.getSize()));
        List<MappedBean> items = null;
        if (page.getCurrent() > 0 && page.getCurrent() <= page.getTotal()) {
            items = repository.page(page);
        }
        return new ResponsePage<MappedBean>(items, page);
    }

    /**
     * Returns authors count
     *
     * @return Total authors count
     */
    public Long count(){
        return repository.count();
    }

    /**
     * Checks existing
     *
     * @param id Tag id
     * @return True if exists
     */
    public Boolean exists(Long id){
        return repository.exists(id);
    }

    /**
     * Checks existing
     *
     * @param name Tag name
     * @return True if exists
     */
    public Boolean existsByName(String name){
        return repository.existByName(name);
    }

}
