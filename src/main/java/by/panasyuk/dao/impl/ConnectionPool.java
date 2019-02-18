package by.panasyuk.dao.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private static ConnectionPool instance;
    private static Lock lockForSingleTone = new ReentrantLock();
    private  final String driverClass;
    private  final String jdbcUrl;
    private  String user;
    private  String password;
    private  int poolCapacity;
    Lock lock = new ReentrantLock();
    volatile Deque<Connection> deque = new ArrayDeque<>();

    private ConnectionPool(String driverClass, String jdbcUrl, String user, String
            password, int poolCapacity) throws SQLException, ClassNotFoundException {
        this.driverClass = driverClass;
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
        this.poolCapacity = poolCapacity;
        Class.forName(driverClass);
        for (int i = 0; i < poolCapacity; i++) {
            Connection connection = DriverManager.getConnection(jdbcUrl,user,password);
            deque.push(connection);
        }
    }

    public static ConnectionPool getInstance(){
        lockForSingleTone.lock();
        try {
            if (instance == null) {
                int POOL_CAPACITY = 5 ;
                String JDBCDRIVER_CLASS = "com.mysql.cj.jdbc.Driver" ;
                String DB_URL = "jdbc:mysql://207.154.220.222:3306/pharmacy?useSSL=false" ;
                String DB_USER = "dzianis_panasyuk" ;
                String DB_PASSWORD = "un38B6Fd4hBv2";
                try {
                    instance = new ConnectionPool(JDBCDRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD , POOL_CAPACITY);
                } catch (SQLException e) {
                    throw new IllegalArgumentException("Driver manager failed to connect to DataBase");
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException("can't load JdbcDriverClass");
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

    public Connection getConnection() {
        try {
            lock.lock();
            while (deque.isEmpty()) {
                }
            Connection connection = deque.poll();
            InvocationHandler handler = new Handler(connection);
            return (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(), connection.getClass().getInterfaces(), handler);
        }
        finally {
            lock.unlock();
        }
    }


    void releaseConnection(Connection connection) {
        deque.push(connection);
    }
}