package by.panasyuk.controller.command;

import by.panasyuk.service.LoginService;
import by.panasyuk.service.PasswordService;
import by.panasyuk.service.UserService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login implements Command {

    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        LoginService loginService = LoginService.getInstance();
        PasswordService passwordService = PasswordService.getInstance();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            password = passwordService.passwordHash(password);
            if (!loginService.login(login,password)){
               // req.setAttribute("invalidLogin",true);
                return "/WEB-INF/views/login.jsp";
            }
            else{
                req.setAttribute("login",login);
                return "/WEB-INF/views/successfulLogin.jsp";
            }
        }catch (ServiceException | NoSuchAlgorithmException e){
            throw new CommandException(e);
        }

    }
}
