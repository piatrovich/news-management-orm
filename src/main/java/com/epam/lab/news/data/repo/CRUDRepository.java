package com.epam.lab.news.data.repo;

import java.util.List;

public interface CRUDRepository<E> {

    public List<E> all();

    public E one(Long id);

    public void save(E entity);

    public Long count();

    public void delete(E entity);

    public boolean exists(Long id);

}
