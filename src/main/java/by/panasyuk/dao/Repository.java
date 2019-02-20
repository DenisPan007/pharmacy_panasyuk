package by.panasyuk.dao;

import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.dao.specification.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
     * @throws DaoException should be clarify
     */
    T add(T object) throws DaoException;

    /**
     * Update identified entity
     * @param object identified entity
     * @throws DaoException should be clarify
     */
    void update(T object) throws DaoException;

    /**
     * Delete identified entity
     * @param object identified entity
     * @throws DaoException should be clarify
     */
    void delete(T object) throws DaoException;
    List<T> getQuery(T obj, Specification<T> spec) throws DaoException;
}
