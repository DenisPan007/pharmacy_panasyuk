package by.panasyuk.repository;

import by.panasyuk.repository.exception.RepositoryException;

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
    <T extends Identified<PK>, PK extends Serializable> Repository<T, PK> getTransactionalRepository(Supplier<Repository<T,PK>> supplier);
}
