package by.panasyuk.dao.impl;

import by.panasyuk.dao.*;
import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.domain.User;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * Jdbc DAO Factory
 */
public class JdbcDaoFactory implements DaoFactory, TransactionalDaoFactory {
    private static JdbcDaoFactory instance;
    private static Lock lock = new ReentrantLock();
    private Map<Class, Supplier<Repository>> creators = new HashMap<>();

    private class DaoInvocationHandler implements InvocationHandler {
        private final Repository dao;

        DaoInvocationHandler(Repository dao) {
            this.dao = dao;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;

            if (Arrays.stream(dao.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(AutoConnection.class))
                    .map(Method::getName)
                    .anyMatch(m -> m.equals(method.getName()))) {

                ConnectionPool connectionPool = ConnectionPool.getInstance();
                Connection connection = connectionPool.getConnection();

                TransactionManager.setConnectionWithReflection(dao, connection);

                result = method.invoke(dao, args);

                connection.close();
                TransactionManager.setConnectionWithReflection(dao, null);

            } else {
                result = method.invoke(dao, args);
            }

            return result;
        }

    }

    private JdbcDaoFactory() {
        creators.put(User.class, UserRepository::new);
    }

    public static JdbcDaoFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new JdbcDaoFactory();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    @Override
    public <T extends Identified<PK>, PK extends Serializable> Repository<T, PK> getDao(Class<T> entityClass)  {
        Supplier<Repository> daoCreator = creators.get(entityClass);
        Repository dao = daoCreator.get();

        return (Repository) Proxy.newProxyInstance(dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),
                new DaoInvocationHandler(dao));
    }

    @Override
    public <T extends Identified<PK>, PK extends Serializable> Repository<T, PK> getTransactionalDao(Class<T> entityClass) throws DaoException {
        Supplier<Repository> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity Class cannot be find");
        }

        return daoCreator.get();
    }
}