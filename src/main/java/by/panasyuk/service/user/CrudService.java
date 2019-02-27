package by.panasyuk.service.user;

import by.panasyuk.dao.exception.RepositoryException;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CrudService extends UserService {
    private static CrudService instance;
    private static Lock lockForSingleTone = new ReentrantLock();

    public static CrudService getInstance() {
        lockForSingleTone.lock();
        try {
            if (instance == null) {
                instance = new CrudService();
            }

        } finally {
            lockForSingleTone.unlock();
        }
        return instance;
    }
    public void delete(int id) throws ServiceException {
        User user = new User();
        user.setId(id);
        try {
            userRepository.delete(user);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }
}
