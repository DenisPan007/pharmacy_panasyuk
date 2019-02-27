package by.panasyuk.service.user;

import by.panasyuk.dao.RepositoryFactory;
import by.panasyuk.dao.Repository;
import by.panasyuk.dao.impl.JdbcRepositoryFactory;
import by.panasyuk.dao.impl.UserRepository;
import by.panasyuk.domain.User;

public abstract class UserService {
    protected RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    protected Repository<User, Integer> userRepository = repositoryFactory.getRepository(UserRepository::new);

    public UserService() {
    }

}