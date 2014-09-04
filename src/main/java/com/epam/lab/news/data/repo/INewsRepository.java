package com.epam.lab.news.data.repo;

import com.epam.lab.news.bean.MappedBean;

import java.util.List;

public interface INewsRepository extends PagingRepository<MappedBean> {

    List<MappedBean> mostCommented(Long count);

}
