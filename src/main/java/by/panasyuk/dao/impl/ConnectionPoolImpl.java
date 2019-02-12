package by.panasyuk.dao.impl;

import by.panasyuk.dao.ConnectionPool;
import by.panasyuk.dao.exception.ConnectionPoolException;

import java.sql.Connection;

/**
 * Implementation of Connection Pool
 */
public class ConnectionPoolImpl implements ConnectionPool {
    private static volatile ConnectionPool instance;

    private ConnectionPoolImpl() {}

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPoolImpl.class) {
                if (instance == null) {
                    instance = new ConnectionPoolImpl();
                }
            }
        }

        return instance;
    }

    @Override
    public Connection retrieveConnection() throws ConnectionPoolException {

        //provide your code here

        throw new UnsupportedOperationException();
    }

    @Override
    public void putBackConnection(Connection connection) {

        //provide your code here

        throw new UnsupportedOperationException();
    }

    @Override
    public void destroyPool() throws ConnectionPoolException {

        //provide your code here

        throw new UnsupportedOperationException();
    }
}
