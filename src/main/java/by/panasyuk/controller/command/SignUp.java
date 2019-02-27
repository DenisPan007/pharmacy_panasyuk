package by.panasyuk.controller.command;

import by.panasyuk.domain.User;
import by.panasyuk.service.UserService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

public class SignUp implements Command {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        UserService userService = UserService.getInstance();
        try {
            password = Login.passwordHash(password);
            if (userService.isReservedLogin(login)) {
                req.setAttribute("error","this login is reserved");
                return "/WEB-INF/views/register.jsp";
            } else if (userService.isReservedEmail(email)) {
                req.setAttribute("error","this email is reserved");
                return "/WEB-INF/views/register.jsp";
            } else {
                User user = userService.signUp(login, password, email);
                req.setAttribute("user", user);
                return "/WEB-INF/views/successful_registration.jsp";
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new CommandException("There is no such algorithm",e);
        }
    }
}
