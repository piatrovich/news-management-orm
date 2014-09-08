package com.epam.lab.news.data.bean;

import java.util.Collection;

/**
 * Describes response page
 *
 * @author Dzmitry Piatrovich
 * @param <T> Entity class
 */
public class ResponsePage<T> {
    private Collection<T> items;
    private Page page;

    /**
     * Constructor
     *
     * @param collection Collection of objects
     * @param page Page object
     */
    public ResponsePage(Collection<T> collection, Page page){
        this.items = collection;
        this.page = page;
    }

    /**
     * Returns response items
     *
     * @return Response objects
     */
    public Collection<T> getItems() {
        return items;
    }

    /**
     * Returns Page object with information for pagination
     *
     * @return Page object
     */
    public Page getPage() {
        return page;
    }
}
