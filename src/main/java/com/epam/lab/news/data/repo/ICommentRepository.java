package com.epam.lab.news.data.repo;

import com.epam.lab.news.bean.Comment;

import java.util.List;

public interface ICommentRepository extends PagingAndSortingRepository<Comment> {

    List<Comment> findByNewsId(Long id);

    Long getCountByNewsId(Long newsId);

}
