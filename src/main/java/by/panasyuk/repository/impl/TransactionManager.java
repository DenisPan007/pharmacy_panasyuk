package by.panasyuk.repository.impl;

import by.panasyuk.repository.AbstractJdbcRepository;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.exception.ConnectionPoolException;
import by.panasyuk.repository.exception.RepositoryException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of transaction with Repository
 */
public final class TransactionManager {
    private Connection connection;

    public void begin( Repository... repositories) throws RepositoryException{
        try{
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
        for(int i = 0;i<repositories.length;i++) {
            setConnection(repositories[i], connection);
        }

        } catch (ConnectionPoolException |SQLException e) {
            throw new RepositoryException("Failed to get a connection from CP.", e);
        }
    }

    public void end() {

        //provide your code here

        throw new UnsupportedOperationException();
    }

    public void commit()  throws RepositoryException {

        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RepositoryException("Failed to commit.", e);
        }
    }

    public void rollback() throws RepositoryException {

        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RepositoryException("Failed to rollBack.", e);
        }

    }


    static void setConnection(Object repository, Connection connection) throws RepositoryException {
        if (!(repository instanceof AbstractJdbcRepository)) {
            throw new RepositoryException("Repository implementation does not extend AbstractJdbcRepository.");
        }
        AbstractJdbcRepository abstrRepo = (AbstractJdbcRepository) repository;
        abstrRepo.setConnection(connection);
    }

}
