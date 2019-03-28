package by.panasyuk.controller.command;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.service.user.CrudUserService;
import by.panasyuk.service.user.PasswordService;
import by.panasyuk.service.validation.PasswordValidator;
import by.panasyuk.service.validation.ValidationService;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeUserPassword implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        CrudUserService userService = new CrudUserService();
        PasswordService passwordService = new PasswordService();
        ValidationService validationService = new PasswordValidator();
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        if (!validationService.isValid(newPassword)){
            request.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.account") + "&error=incorrectAuthentication";
        }
        try{
            oldPassword = passwordService.passwordHash(oldPassword);
            if(!user.getPassword().equals(oldPassword)){
                request.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.account") + "&error=incorrectPassword";
            }
            newPassword = passwordService.passwordHash(newPassword);
            user.setPassword(newPassword);
            userService.update(user);
            request.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.account")+"&message=successChange";
        }
        catch (ServiceException e){
            throw new CommandException(e);
        }
    }
}
