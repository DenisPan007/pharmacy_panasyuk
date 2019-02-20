package by.panasyuk.dao;



import by.panasyuk.dao.exception.RepositoryException;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * Transactional DAO Factory
 */
public interface TransactionalDaoFactory {
    /**
     * Get generic DAO of entity without connection
     * @return transactional DAO
     * @throws RepositoryException should be clarify
     */
    <T extends Identified<PK>, PK extends Serializable> Repository getTransactionalDao(Supplier<Repository<T,PK>> supplier) throws RepositoryException;
}