package com.epam.lab.news.data.repo;

import java.util.List;

public interface CRUDRepository<E> {

    List<E> all();

    E one(Long id);

    E save(E entity);

    Long count();

    void delete(E entity);

    boolean exists(Long id);

}
