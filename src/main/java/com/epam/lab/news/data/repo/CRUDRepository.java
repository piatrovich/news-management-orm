package com.epam.lab.news.data.repo;

import java.util.List;

/**
 * Defines abstraction for CRUD operations with entities
 *
 * @author Dzmitry Piatrovich
 * @param <E> Entity class
 */
public interface CRUDRepository<E> {

    /**
     * Return list of all available entities
     *
     * @return List of all entities
     */
    List<E> all(Long...params);

    /**
     * Returns single entity by ID
     *
     * @param id Entity ID
     * @return Entity
     */
    E one(Long id);

    /**
     * Saves and returns object with the same values as in table
     *
     * @param entity Entity
     * @return Saved entity
     */
    E save(E entity);

    /**
     * Returns count of all available objects
     *
     * @return Long number
     */
    Long count();

    /**
     * Deletes object from DB
     *
     * @param entity Application bean
     */
    void delete(E entity);

    /**
     * Checks object existing in DB
     *
     * @param id Entity ID
     * @return True if exist, otherwise false
     */
    boolean exists(Long id);

}
