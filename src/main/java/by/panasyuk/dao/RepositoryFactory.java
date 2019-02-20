package by.panasyuk.dao;

import by.panasyuk.dao.exception.RepositoryException;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * Repository Factory
 */
public interface RepositoryFactory {
    /**
     * Return implementation of Repository for entity class
     * @return - implementation of Repository for entity class
     * @throws RepositoryException - should be clarify
     */
    <T extends Identified<PK>, PK extends Serializable> Repository<T, PK> getRepository(Supplier<Repository<T,PK>> supplier);
}
