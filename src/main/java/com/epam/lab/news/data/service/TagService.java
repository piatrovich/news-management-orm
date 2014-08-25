package com.epam.lab.news.data.service;

import com.epam.lab.news.bean.Tag;
import com.epam.lab.news.data.bean.Page;
import com.epam.lab.news.data.bean.ResponsePage;
import com.epam.lab.news.data.repo.PagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class TagService {
    @Autowired
    @Qualifier("tagRepository")
    PagingAndSortingRepository repository;

    public List all(){
        return repository.all();
    }

    public Tag get(Long id){
        return (Tag) repository.one(id);
    }

    public void save(Tag tag){
        repository.save(tag);
    }

    public void delete(Long id){
        Tag tag = (Tag) repository.one(id);
        if(tag != null){
            repository.delete(tag);
        }
    }

    public ResponsePage<Tag> getPage(Page page){
        page.setTotal(repository.pageCount(page.getSize()));
        List<Tag> items = null;
        if (page.getCurrent() == 1) {
            items = repository.firstPage(page.getSize());
        } else if (page.getCurrent() > 1 && page.getCurrent() <= page.getTotal()){
            items = repository.page(page);
        }
        return new ResponsePage<Tag>(items, page);
    }

}
