package by.panasyuk.controller.command;

import by.panasyuk.domain.User;
import by.panasyuk.service.user.PasswordService;
import by.panasyuk.service.user.PresentChecker;
import by.panasyuk.service.user.SignUpService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.service.validation.EmailValidator;
import by.panasyuk.service.validation.LoginValidator;
import by.panasyuk.service.validation.PasswordValidator;
import by.panasyuk.service.validation.ValidationService;
import by.panasyuk.util.PathManager;
import by.panasyuk.util.RoleEnum;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class SignUp implements Command {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String address = req.getParameter("address");

        ValidationService loginValidator = new LoginValidator();
        ValidationService passwordValidator = new PasswordValidator();
        ValidationService emailValidator = new EmailValidator();
        if(login==null||!loginValidator.isValid(login)){
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.sign.up") +"&error=invalidLogin";
        }
        if(password==null||!passwordValidator.isValid(password)){
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.sign.up")+"&error=invalidPassword";
        }
        if(email==null||!emailValidator.isValid(email)){
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.sign.up")+"&error=invalidEmail";
        }
        PresentChecker presentChecker = new PresentChecker();
        SignUpService signUpService = new SignUpService();
        PasswordService passwordService = new PasswordService();
        try {
            password = passwordService.passwordHash(password);
            if (presentChecker.isReservedLogin(login)) {
                req.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.sign.up")+"&error=reservedLogin";
            }
            if (presentChecker.isReservedEmail(email)) {
                req.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.sign.up")+"&error=reservedEmail";
            }
                User user = signUpService.signUp(login, password, name,lastName,email);
                session.setAttribute("user", user);
                session.setAttribute("role", RoleEnum.valueOf(user.getRole()));
                req.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.account");
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new CommandException("There is no such algorithm", e);
        }
    }
}
