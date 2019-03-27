package by.panasyuk.controller.command;

import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.service.user.CrudUserService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

public class ChangeUser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
       String login = request.getParameter("login");
        String role = request.getParameter("role");
        CrudUserService userService = new CrudUserService();
        try {
            User user = userService.getByLogin(login);
            if (user == null) {
                throw new CommandException("can't get user by Id");
            }
            user.setRole(role);
            userService.update(user);
            Gson gson = new Gson();
            return gson.toJson(user);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
