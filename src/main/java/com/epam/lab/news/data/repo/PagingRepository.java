package com.epam.lab.news.data.repo;

import com.epam.lab.news.data.bean.Page;

import java.util.List;

public interface PagingRepository<E> extends CRUDRepository<E> {

    List<E> page(Page page, Long...params);

    Long pageCount(Long pageSize, Long...params);

}
