package by.panasyuk.controller.command;

import by.panasyuk.service.user.LoginService;
import by.panasyuk.service.user.PasswordService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

public class Login implements Command {

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        LoginService loginService = LoginService.getInstance();
        PasswordService passwordService = PasswordService.getInstance();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            password = passwordService.passwordHash(password);
            if (!loginService.login(login,password)){
                req.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.login") +"&error=incorrectAuthentication";
            }
            else{

                if (login.equals("admin")) {
                    session.setAttribute("role",RoleEnum.ADMIN);
                }
                else{
                    session.setAttribute("role",RoleEnum.CLIENT);
                }
                session.setAttribute("login",login);
                req.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.initial");
            }
        }catch (ServiceException | NoSuchAlgorithmException e){
            throw new CommandException(e);
        }

    }
}
