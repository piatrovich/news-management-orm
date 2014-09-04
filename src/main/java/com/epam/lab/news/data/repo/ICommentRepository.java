package com.epam.lab.news.data.repo;

import com.epam.lab.news.bean.Comment;

import java.util.List;

public interface ICommentRepository extends PagingRepository<Comment> {

    List<Comment> findByNewsId(Long id);

    Long getCountByNewsId(Long newsId);

}
