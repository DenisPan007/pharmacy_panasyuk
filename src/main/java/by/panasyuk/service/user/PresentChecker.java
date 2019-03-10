package by.panasyuk.service.user;

import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.user.GetByEmail;
import by.panasyuk.repository.specification.user.GetByLogin;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PresentChecker extends UserService {

    public boolean isReservedLogin(String login) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        try {
            Specification<User> spec = new GetByLogin();
            List list = userRepository.getQuery(user, spec);
            return !list.isEmpty();
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to check present of user in base ", e);
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
            throw new ServiceException("Failed to check present of user in base ", e);
        }
    }
}
