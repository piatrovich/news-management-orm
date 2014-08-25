package com.epam.lab.news.data.bean;

public class Page {
    private Long total;
    private Long current;
    private Long size;

    public Page(Long page, Long size){
        this.current = page;
        this.size = size;
    }

    public Long getCurrent() {
        return current;
    }

    public Long getSize() {
        return size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
