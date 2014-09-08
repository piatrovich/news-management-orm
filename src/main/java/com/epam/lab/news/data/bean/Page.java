package com.epam.lab.news.data.bean;

/**
 * Describes items page
 *
 * @author Dzmitry Piatrovich
 */
public class Page {
    private Long total;
    private Long current;
    private Long size;

    /**
     * Constructor
     *
     * @param page Number of page
     * @param size Items on page
     */
    public Page(Long page, Long size){
        this.current = page;
        this.size = size;
    }

    /**
     * Returns number of current page
     *
     * @return Current page number
     */
    public Long getCurrent() {
        return current;
    }

    /**
     * Returns page size
     *
     * @return Number of items on page
     */
    public Long getSize() {
        return size;
    }

    /**
     * Returns number of total available pages
     *
     * @return Number of available pages
     */
    public Long getTotal() {
        return total;
    }

    /**
     * Sets number of total pages
     *
     * @param total Number of total pages
     */
    public void setTotal(Long total) {
        this.total = total;
    }
}
