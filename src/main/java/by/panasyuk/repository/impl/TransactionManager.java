package by.panasyuk.repository.impl;

import by.panasyuk.repository.AbstractJdbcRepository;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.exception.ConnectionPoolException;
import by.panasyuk.repository.exception.RepositoryException;

import java.sql.Connection;

/**
 * Implementation of transaction with DAO
 */
public final class TransactionManager {
    private Connection proxyConnection;

    public void begin(Repository dao, Repository... daos) throws RepositoryException, InterruptedException, ConnectionPoolException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();


            proxyConnection = connectionPool.getConnection();
            setConnection(dao, proxyConnection);

        //} catch (ConnectionPoolException e) {
        //    throw new RepositoryException("Failed to get a connection from CP.", e);
        //}

        //provide your code here

        throw new UnsupportedOperationException();
    }

    public void end() {

        //provide your code here

        throw new UnsupportedOperationException();
    }

    public void commit() {

        //provide your code here

        throw new UnsupportedOperationException();
    }

    public void rollback() {

        //provide your code here

        throw new UnsupportedOperationException();
    }


    static void setConnection(Object repository, Connection connection) throws RepositoryException {
        if (!(repository instanceof AbstractJdbcRepository)) {
            throw new RepositoryException("Repository implementation does not extend AbstractJdbcRepository.");
        }
        AbstractJdbcRepository abstrRepo = (AbstractJdbcRepository) repository;
        abstrRepo.setConnection(connection);
    }

}
