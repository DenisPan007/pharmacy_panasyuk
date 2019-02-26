package by.panasyuk.controller.command;

import by.panasyuk.service.UserService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        UserService service = UserService.getInstance();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            if (!service.login(login,password)){
               // req.setAttribute("invalidLogin",true);
                return "/WEB-INF/views/login.jsp";
            }
            else{
                req.setAttribute("login",login);
                return "/WEB-INF/views/successfulLogin.jsp";
            }
        }catch (ServiceException e){
            throw new CommandException(e);
        }

    }
}
