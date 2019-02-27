package by.panasyuk.service;

import by.panasyuk.dao.exception.RepositoryException;
import by.panasyuk.dao.specification.GetByEmail;
import by.panasyuk.dao.specification.GetByLogin;
import by.panasyuk.dao.specification.Specification;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PresentChecker extends UserService {
    private static PresentChecker instance;
    private static Lock lockForSingleTone = new ReentrantLock();

    public static PresentChecker getInstance() {
        lockForSingleTone.lock();
        try {
            if (instance == null) {
                instance = new PresentChecker();
            }

        } finally {
            lockForSingleTone.unlock();
        }

        return instance;
    }
    public boolean isReservedLogin(String login) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        try {
            Specification<User> spec = new GetByLogin();
            List list = userRepository.getQuery(user, spec);
            return !list.isEmpty();
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    public boolean isReservedEmail(String email) throws ServiceException {
        User user = new User();
        user.setEmail(email);
        try {
            Specification<User> spec = new GetByEmail();
            List list = userRepository.getQuery(user, spec);
            return !list.isEmpty();
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }
}
