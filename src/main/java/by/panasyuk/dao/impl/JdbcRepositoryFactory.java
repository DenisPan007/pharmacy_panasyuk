package by.panasyuk.dao.impl;

import by.panasyuk.dao.*;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * Jdbc DAO Factory
 */
public class JdbcRepositoryFactory implements RepositoryFactory{
    private static JdbcRepositoryFactory instance;
    private static Lock lock = new ReentrantLock();

    private class DaoInvocationHandler implements InvocationHandler {
        private final Repository repository;

        DaoInvocationHandler(Repository repository) {
            this.repository = repository;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;

            if (Arrays.stream(repository.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(WithoutConnection.class))
                    .map(Method::getName)
                    .anyMatch(m -> m.equals(method.getName()))) {

                result = method.invoke(repository, args);

            } else {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                Connection connection = connectionPool.getConnection();

                TransactionManager.setConnectionWithReflection(repository, connection);

                result = method.invoke(repository, args);

                connection.close();
                TransactionManager.setConnectionWithReflection(repository, null);
            }

            return result;
        }

    }

    private JdbcRepositoryFactory() {
    }

    public static JdbcRepositoryFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new JdbcRepositoryFactory();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    @Override
    public <T extends Identified<PK>, PK extends Serializable> Repository<T, PK> getRepository(Supplier<Repository<T,PK>> supplier)  {
        Repository<T,PK> repository = supplier.get();

        return (Repository<T,PK>) Proxy.newProxyInstance(repository.getClass().getClassLoader(),
                repository.getClass().getInterfaces(),
                new DaoInvocationHandler(repository));
    }
}