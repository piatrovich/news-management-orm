package com.epam.lab.news.database.data.repo;

import com.epam.lab.news.bean.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Defines custom repository providing CRUD operations. <br />
 * Working with instances of Article class.
 *
 * @author Dzmitry Piatrovich
 * @since 1.0.1-alpha
 */
@Repository
public interface NewsRepository extends CrudRepository<Article, Long> {
}
