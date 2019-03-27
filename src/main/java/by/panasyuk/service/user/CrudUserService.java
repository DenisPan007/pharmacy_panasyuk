package by.panasyuk.service.user;

import by.panasyuk.domain.User;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.repository.specification.user.GetAllUsers;
import by.panasyuk.repository.specification.user.GetByLogin;
import by.panasyuk.repository.specification.user.GetUserById;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;

public class CrudUserService extends UserService {

    public void update(User user) throws ServiceException {
        try {
            userRepository.update(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(int id) throws ServiceException {
        User user = new User();
        user.setId(id);
        try {
            userRepository.delete(user);
        } catch (RepositoryException e) {
            throw new ServiceException("Cant't delete user ", e);
        }
    }

    public User getById(int id) throws ServiceException {
        Specification<User> spec = new GetUserById();
        User user = new User();
        user.setId(id);
        try {
            List<User> userList = userRepository.getQuery(user, spec);
            if (userList.isEmpty()) {
                return null;
            }
            return userList.get(0);
        } catch (RepositoryException e) {
            throw new ServiceException("Cant't get user ", e);
        }
    }
    public User getByLogin(String login) throws ServiceException {
        Specification<User> spec = new GetByLogin();
        User user = new User();
        user.setLogin(login);
        try {
            List<User> userList = userRepository.getQuery(user, spec);
            if (userList.isEmpty()) {
                return null;
            }
            return userList.get(0);
        } catch (RepositoryException e) {
            throw new ServiceException("Cant't get user ", e);
        }
    }

    public List<User> getAll() throws ServiceException {
        Specification<User> spec = new GetAllUsers();
        try {
            return userRepository.getQuery(new User(), spec);
        } catch (RepositoryException e) {
            throw new ServiceException("Cant't get user ", e);
        }
    }
}
