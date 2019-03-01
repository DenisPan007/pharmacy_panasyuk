package by.panasyuk.service.user;

import by.panasyuk.repository.RepositoryFactory;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.impl.JdbcRepositoryFactory;
import by.panasyuk.repository.impl.UserRepository;
import by.panasyuk.domain.User;

public abstract class UserService {
    protected RepositoryFactory repositoryFactory = JdbcRepositoryFactory.getInstance();
    protected Repository<User, Integer> userRepository = repositoryFactory.getRepository(UserRepository::new);

    public UserService() {
    }

}