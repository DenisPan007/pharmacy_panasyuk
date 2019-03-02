package by.panasyuk.controller.command;

import by.panasyuk.domain.User;
import by.panasyuk.service.user.PasswordService;
import by.panasyuk.service.user.PresentChecker;
import by.panasyuk.service.user.SignUpService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

public class SignUp implements Command {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        PresentChecker presentChecker = PresentChecker.getInstance();
        SignUpService signUpService = SignUpService.getInstance();
        PasswordService passwordService = PasswordService.getInstance();
        try {
            password = passwordService.passwordHash(password);
            if (presentChecker.isReservedLogin(login)) {
                session.setAttribute("errorSignUp","this login is reserved");
                req.setAttribute("route", Router.Type.REDIRECT);
                return "/start?command=toSignUpPage";
            } else if (presentChecker.isReservedEmail(email)) {
                session.setAttribute("error","this email is reserved");
                req.setAttribute("route", Router.Type.REDIRECT);
                return "/start?command=toSignUpPage";
            } else {
                User user = signUpService.signUp(login, password, email);
                session.setAttribute("login", login);
                session.setAttribute("role", "client");
                req.setAttribute("route",Router.Type.REDIRECT);
                return "/start?command=toAccount";
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new CommandException("There is no such algorithm",e);
        }
    }
}
