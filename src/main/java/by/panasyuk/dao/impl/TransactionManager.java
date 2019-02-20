package by.panasyuk.dao.impl;

import by.panasyuk.dao.AbstractJdbcRepository;
import by.panasyuk.dao.Repository;
import by.panasyuk.dao.exception.DaoException;

import java.lang.reflect.Field;
import java.sql.Connection;

/**
 * Implementation of transaction with DAO
 */
public final class TransactionManager {
    private Connection proxyConnection;

    public void begin(Repository dao, Repository... daos) throws DaoException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();


            proxyConnection = connectionPool.getConnection();
            setConnectionWithReflection(dao, proxyConnection);

        //} catch (ConnectionPoolException e) {
        //    throw new DaoException("Failed to get a connection from CP.", e);
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


    static void setConnectionWithReflection(Object dao, Connection connection) throws DaoException {
        if (!(dao instanceof AbstractJdbcRepository)) {
            throw new DaoException("DAO implementation does not extend AbstractJdbcRepository.");
        }

        try {

            Field connectionField = AbstractJdbcRepository.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, connection);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DaoException("Failed to set connection for transactional DAO. ", e);
        }
    }

}
