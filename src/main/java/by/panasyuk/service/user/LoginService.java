package by.panasyuk.service.user;

import by.panasyuk.domain.User;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.repository.specification.user.GetByLogin;
import by.panasyuk.service.exception.ServiceException;

import java.util.List;

public class LoginService extends UserService {

    public User login(String login, String password) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        try {
            Specification<User> spec = new GetByLogin();
            List<User> list = userRepository.getQuery(user, spec);
            if (list.isEmpty()) {
                return null;
            }
            user = list.get(0);
            if (!user.getPassword().equals(password)) {
                return null;
            }
            return user;

        } catch (RepositoryException e) {
            throw new ServiceException("Failed to login user", e);
        }
    }
}
