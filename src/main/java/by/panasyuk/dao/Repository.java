package by.panasyuk.dao;

import by.panasyuk.dao.exception.RepositoryException;
import by.panasyuk.dao.specification.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO
 * @param <T> - Identified entity
 * @param <PK> - Entity primary key
 */
public interface Repository<T extends Identified<PK>, PK extends Serializable> {
    /**
     * Save identified entity in DB
     * @param object identified entity
     * @return identified entity in DB
     * @throws RepositoryException should be clarify
     */
    T add(T object) throws RepositoryException;

    /**
     * Update identified entity
     * @param object identified entity
     * @throws RepositoryException should be clarify
     */
    void update(T object) throws RepositoryException;

    /**
     * Delete identified entity
     * @param object identified entity
     * @throws RepositoryException should be clarify
     */
    void delete(T object) throws RepositoryException;
    List<T> getQuery(T obj, Specification<T> spec) throws RepositoryException;
}
