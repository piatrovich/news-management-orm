package com.epam.lab.news.data.repo;

import com.epam.lab.news.data.bean.Page;

import java.util.List;

public interface PagingAndSortingRepository<T> extends CRUDRepository<T> {

    public List<T> firstPage(Long size);

    public List<T> page(Page page);

    public Long pageCount(Long pageSize);

    /*public List<T> nextPage(Page page);

    public boolean hasNextPage(Page page);

    public List<T> previousPage(Page page);

    public boolean hasPreviousPage(Page page);*/

}
