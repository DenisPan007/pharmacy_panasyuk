package by.panasyuk.service.impl;

import by.panasyuk.dao.DaoFactory;
import by.panasyuk.dao.DaoFactoryType;
import by.panasyuk.dao.FactoryProducer;
import by.panasyuk.dao.GenericDao;
import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.dao.exception.PersistException;
import by.panasyuk.domain.User;
import by.panasyuk.service.UserService;
import by.panasyuk.service.exception.ServiceException;

/**
 * Example of user service implementation
 */
public class UserServiceImpl implements UserService {
    @Override
    public User signUp(User user) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);

        //provide your code here

        try {
            GenericDao<User, Integer> userDao = daoFactory.getDao(User.class);
            userDao.persist(user);

        } catch (DaoException e) {
            throw new ServiceException("Failed to get user DAO. ", e);

        } catch (PersistException e) {
            throw new ServiceException("Failed to save user. ", e);
        }

        //provide your code here

        throw new UnsupportedOperationException();
    }
}
