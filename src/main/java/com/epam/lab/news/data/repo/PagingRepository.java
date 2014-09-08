package com.epam.lab.news.data.repo;

import com.epam.lab.news.data.bean.Page;

import java.util.List;

/**
 * Defines paging repository abstraction
 * @param <E> Entity
 */
public interface PagingRepository<E> extends CRUDRepository<E> {

    /**
     * Returns items on page
     *
     * @param page Page
     * @param params For custom implementations
     * @return Items on page
     */
    List<E> page(Page page, Long...params);

    /**
     * Returns number of pages by size
     *
     * @param pageSize Size of page
     * @param params For custom implementations
     * @return Number of pages
     */
    Long pageCount(Long pageSize, Long...params);

}
