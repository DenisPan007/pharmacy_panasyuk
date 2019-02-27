package by.panasyuk.service;

import by.panasyuk.dao.exception.RepositoryException;
import by.panasyuk.dao.specification.GetByLogin;
import by.panasyuk.dao.specification.Specification;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoginService extends UserService {
    private static LoginService instance;
    private static Lock lockForSingleTone = new ReentrantLock();

    public static LoginService getInstance() {
        lockForSingleTone.lock();
        try {
            if (instance == null) {
                instance = new LoginService();
            }

        } finally {
            lockForSingleTone.unlock();
        }

        return instance;
    }
    public boolean login(String login, String password) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        try {
            Specification<User> spec = new GetByLogin();
            List<User> list = userRepository.getQuery(user, spec);
            if (list.isEmpty()) {
                return false;
            }
            user = list.get(0);
            return (user.getPassword().equals(password));


        } catch (RepositoryException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }
}
