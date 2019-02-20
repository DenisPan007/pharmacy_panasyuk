package by.panasyuk.dao;



import by.panasyuk.dao.exception.DaoException;

import java.io.Serializable;

/**
 * Transactional DAO Factory
 */
public interface TransactionalDaoFactory {
    /**
     * Get generic DAO of entity without connection
     * @param entityClass
     * @return transactional DAO
     * @throws DaoException should be clarify
     */
    <T extends Identified<PK>, PK extends Serializable> Repository getTransactionalDao(Class<T> entityClass) throws DaoException;
}