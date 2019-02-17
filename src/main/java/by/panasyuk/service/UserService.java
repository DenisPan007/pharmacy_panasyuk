package by.panasyuk.service;

import by.panasyuk.dao.DaoFactory;
import by.panasyuk.dao.DaoFactoryType;
import by.panasyuk.dao.FactoryProducer;
import by.panasyuk.dao.GenericDao;
import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.dao.impl.ConnectionPool;
import by.panasyuk.dao.specification.GetByLogin;
import by.panasyuk.dao.specification.Specification;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class UserService {
    private static UserService instance;
    private static Lock lockForSingleTone = new ReentrantLock();

    public static UserService getInstance() {
        lockForSingleTone.lock();
        try {
            if (instance == null) {
                instance = new UserService();
            }

        } finally {
            lockForSingleTone.unlock();
        }

        return instance;
    }

    private UserService() {
    }

    public User signUp(String login,String password) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        User user = new User(login,password);

        try {
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            Specification<User> spec = new GetByLogin();
            List list = userDao.getQuery(user, spec);
            if (!list.isEmpty()) {
                return null;
            }
            return userDao.persist(user);

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        }
    }

    public User login(User user) throws ServiceException {
        return new User();
    }
}
