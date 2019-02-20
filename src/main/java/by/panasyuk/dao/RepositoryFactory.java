package by.panasyuk.dao;

import by.panasyuk.dao.exception.RepositoryException;

import java.io.Serializable;

/**
 * Repository Factory
 */
public interface RepositoryFactory {
    /**
     * Return implementation of Repository for entity class
     * @param entityClass - entity class
     * @return - implementation of Repository for entity class
     * @throws RepositoryException - should be clarify
     */
    <T extends Identified<PK>, PK extends Serializable> Repository<T, PK> getRepository(Class<T> entityClass);
}
