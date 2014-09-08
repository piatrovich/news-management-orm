package com.epam.lab.news.data.repo;

import com.epam.lab.news.bean.MappedBean;

import java.util.List;

/**
 * Extends base paging repository
 */
public interface INewsRepository extends PagingRepository<MappedBean> {

    /**
     * Returns list of most commented news sorted by DESC
     *
     * @param count List size
     * @return News list
     */
    List<MappedBean> mostCommented(Long count);

    /**
     * Returns number of news by tag id
     *
     * @param id Tag id
     * @return Count
     */
    Integer countByTag(Long id);

    /**
     * Returns list of news by tag id
     *
     * @param id Tag id
     * @return News list
     */
    List<MappedBean> newsByTag(Long id);

    /**
     * Returns number of news by author id
     *
     * @param id Author id
     * @return Count
     */
    Integer countByAuthor(Long id);

    /**
     * Returns news list by author
     *
     * @param id Author id
     * @return News list
     */
    List<MappedBean> newsByAuthor(Long id);

}
