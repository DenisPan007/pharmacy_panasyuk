package by.panasyuk.controller.command;

import by.panasyuk.dao.Repository;
import by.panasyuk.dao.RepositoryFactory;
import by.panasyuk.dao.exception.RepositoryException;
import by.panasyuk.dao.impl.JdbcRepositoryFactory;
import by.panasyuk.dao.impl.UserRepository;
import by.panasyuk.dao.specification.user.GetAll;
import by.panasyuk.dao.specification.Specification;
import by.panasyuk.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUserList implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        RepositoryFactory factory = JdbcRepositoryFactory.getInstance();
        Repository<User,Integer> repository = factory.getRepository(UserRepository::new);
        Specification<User> spec = new GetAll();
        //Specification<User> spec1 = (user,connection)->{return  connection.createStatement().executeQuery("123");};
        try {
            List<User> listUsers = repository.getQuery(new User(), spec);
            request.setAttribute("list",listUsers);
            return  "/WEB-INF/views/admin.jsp";
        } catch (RepositoryException e) {
            throw new CommandException();
        }

    }
}
