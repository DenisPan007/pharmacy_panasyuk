package by.panasyuk.controller.command;

import by.panasyuk.domain.User;
import by.panasyuk.service.user.LoginService;
import by.panasyuk.service.user.PasswordService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.util.PathManager;
import by.panasyuk.util.RoleEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

public class Login implements Command {

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        LoginService loginService = new LoginService();
        PasswordService passwordService = new PasswordService();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            password = passwordService.passwordHash(password);
            User loginedUser = loginService.login(login, password);
            if (loginedUser == null) {
                req.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.login") + "&error=incorrectAuthentication";
            }
            session.setAttribute("role", RoleEnum.valueOf(loginedUser.getRole()));

            session.setAttribute("user", loginedUser);
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.initial");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

    }
}
