package by.panasyuk.service.user;

import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SignUpService extends UserService {
    private static SignUpService instance;
    private static Lock lockForSingleTone = new ReentrantLock();

    public static SignUpService getInstance() {
        lockForSingleTone.lock();
        try {
            if (instance == null) {
                instance = new SignUpService();
            }

        } finally {
            lockForSingleTone.unlock();
        }

        return instance;
    }
    public User signUp(String login, String password, String email) throws ServiceException {
        User user = new User(login, password, email);

        try {
            return userRepository.add(user);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }
}
