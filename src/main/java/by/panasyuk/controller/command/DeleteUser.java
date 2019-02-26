package by.panasyuk.controller.command;

import by.panasyuk.dao.Repository;
import by.panasyuk.dao.RepositoryFactory;
import by.panasyuk.dao.exception.RepositoryException;
import by.panasyuk.dao.impl.JdbcRepositoryFactory;
import by.panasyuk.dao.impl.UserRepository;
import by.panasyuk.dao.specification.GetAll;
import by.panasyuk.dao.specification.Specification;
import by.panasyuk.domain.User;
import by.panasyuk.service.UserService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteUser implements Command {
    public String execute(HttpServletRequest request) throws CommandException {
        UserService service = UserService.getInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            service.delete(id);
            return "User has been deleted";
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
