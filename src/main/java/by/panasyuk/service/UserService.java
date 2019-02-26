package by.panasyuk.service;

import by.panasyuk.controller.command.Login;
import by.panasyuk.dao.RepositoryFactory;
import by.panasyuk.dao.Repository;
import by.panasyuk.dao.exception.RepositoryException;
import by.panasyuk.dao.impl.JdbcRepositoryFactory;
import by.panasyuk.dao.impl.UserRepository;
import by.panasyuk.dao.specification.GetByEmail;
import by.panasyuk.dao.specification.GetByLogin;
import by.panasyuk.dao.specification.Specification;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class UserService {
    private RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    private Repository<User, Integer> userRepository = repositoryFactory.getRepository(UserRepository::new);
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

    public User signUp(String login, String password, String email) throws ServiceException {
        User user = new User(login, password, email);

        try {
            return userRepository.add(user);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
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

    public void delete(int id) throws ServiceException {
        User user = new User();
        user.setId(id);
        try {
            userRepository.delete(user);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        }
    }

    public boolean changePassword(String login, String email) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        try {
            Specification<User> spec = new GetByLogin();
            List<User> list = userRepository.getQuery(user, spec);
            user = list.get(0);
            if (!user.getEmail().equals(email)) {
                return false;
            }
            String newPassword = "newPassword";
            user.setPassword(Login.passwordHash(newPassword));
            userRepository.update(user);
            EmailSender sender = new EmailSender();
            sender.send("your new password", "there is your new password: " + newPassword, email);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to get user DAO. ", e);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Failed to get hash Algorithm. ", e);
        }
        return true;
    }
}