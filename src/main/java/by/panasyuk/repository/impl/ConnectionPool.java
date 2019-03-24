package by.panasyuk.repository.impl;

import by.panasyuk.repository.exception.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private static ConnectionPool instance;
    private static Lock lockForSingleTone = new ReentrantLock();
    private Semaphore availableConnectionAmount = new Semaphore(0);
    private Lock lock = new ReentrantLock();
    private BlockingDeque<Connection> connectionDeque = new LinkedBlockingDeque<>();
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    private static int poolCapacity;


    private ConnectionPool() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        for (int i = 0; i < poolCapacity; i++) {
            Connection connection = DriverManager.getConnection(url, user, password);
            connectionDeque.push(connection);
            availableConnectionAmount.release();
        }
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        lockForSingleTone.lock();
        try {
            if (instance == null) {
                try {
                    InputStream inputStream = ConnectionPool.class.getResourceAsStream("/local_db.properties");
                    Properties properties = new Properties();
                    properties.load(inputStream);
                    url = properties.getProperty("url");
                    driver = properties.getProperty("driver");
                    user = properties.getProperty("user");
                    password = properties.getProperty("password");
                    poolCapacity = Integer.parseInt(properties.getProperty("poolCapacity"));
                    instance = new ConnectionPool();
                } catch (SQLException e) {
                    throw new ConnectionPoolException("Driver manager failed to connect to DataBase", e);
                } catch (ClassNotFoundException e) {
                    throw new ConnectionPoolException("can't load JdbcDriverClass", e);
                } catch (IOException e) {
                    throw new ConnectionPoolException("can't load property file", e);
                }
            }

        } finally {
            lockForSingleTone.unlock();
        }

        return instance;
    }

    private class Handler implements InvocationHandler {
        private Connection connection;

        public Connection getConnection() {
            return connection;
        }

        public Handler(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("close")) {
                releaseConnection(connection);
                return null;
            } else {
                return method.invoke(connection, args);
            }
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        try {
            lock.lock();
            availableConnectionAmount.acquire();
            Connection connection = connectionDeque.poll();
            if(!connection.isValid(3)){
                connection = DriverManager.getConnection(url, user, password);
            }
            InvocationHandler handler = new Handler(connection);
            return (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(), connection.getClass().getInterfaces(), handler);
        } catch (SQLException|InterruptedException e){
            throw new ConnectionPoolException(e);
        }
        finally {
            lock.unlock();
        }
    }


    void releaseConnection(Connection connection) {
        connectionDeque.push(connection);
        availableConnectionAmount.release();
    }
}