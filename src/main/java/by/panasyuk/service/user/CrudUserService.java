package by.panasyuk.service.user;

import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.repository.specification.user.GetAllUsers;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CrudUserService extends UserService {
    private static CrudUserService instance;
    private static Lock lockForSingleTone = new ReentrantLock();

    public static CrudUserService getInstance() {
        lockForSingleTone.lock();
        try {
            if (instance == null) {
                instance = new CrudUserService();
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
    public List<User> getAll() throws  ServiceException{
        Specification<User> spec = new GetAllUsers();
        try {
            return userRepository.getQuery(new User(), spec);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
