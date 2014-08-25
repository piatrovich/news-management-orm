package com.epam.lab.news.data.bean;

import java.util.Collection;

public class ResponsePage<T> {
    private Collection<T> items;
    private Page page;

    public ResponsePage(Collection<T> collection, Page page){
        this.items = collection;
        this.page = page;
    }

    public Collection<T> getItems() {
        return items;
    }

    public Page getPage() {
        return page;
    }
}
