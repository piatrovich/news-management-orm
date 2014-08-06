package com.epam.lab.news.database.data.repo;

import com.epam.lab.news.database.data.bean.Counter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends CrudRepository<Counter, String> {
}
