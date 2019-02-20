package by.panasyuk.service;

import by.panasyuk.dao.DaoFactory;
import by.panasyuk.dao.Repository;
import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.dao.impl.JdbcDaoFactory;
import by.panasyuk.dao.specification.GetByEmail;
import by.panasyuk.dao.specification.GetByLogin;
import by.panasyuk.dao.specification.Specification;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class UserService {
    private DaoFactory daoFactory = JdbcDaoFactory.getInstance();
    private Repository<User, Integer> userDao = daoFactory.getDao(User.class);
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

    public boolean isResevedLogin(String login) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        try {
            Specification<User> spec = new GetByLogin();
            List list = userDao.getQuery(user, spec);
            return !list.isEmpty();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    public boolean isResevedEmail(String email) throws ServiceException {
        User user = new User();
        user.setEmail(email);
        try {
            Specification<User> spec = new GetByEmail();
            List list = userDao.getQuery(user, spec);
            return !list.isEmpty();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    public User signUp(String login, String password, String email) throws ServiceException {
        User user = new User(login, password, email);

        try {
            return userDao.add(user);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    public User login(User user) throws ServiceException {
        return new User();
    }
}
