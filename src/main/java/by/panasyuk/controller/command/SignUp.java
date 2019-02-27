package by.panasyuk.controller.command;

import by.panasyuk.domain.User;
import by.panasyuk.service.PasswordService;
import by.panasyuk.service.PresentChecker;
import by.panasyuk.service.SignUpService;
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

        PresentChecker presentChecker = PresentChecker.getInstance();
        SignUpService signUpService = SignUpService.getInstance();
        PasswordService passwordService = PasswordService.getInstance();
        try {
            password = passwordService.passwordHash(password);
            if (presentChecker.isReservedLogin(login)) {
                req.setAttribute("error","this login is reserved");
                return "/WEB-INF/views/register.jsp";
            } else if (presentChecker.isReservedEmail(email)) {
                req.setAttribute("error","this email is reserved");
                return "/WEB-INF/views/register.jsp";
            } else {
                User user = signUpService.signUp(login, password, email);
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
