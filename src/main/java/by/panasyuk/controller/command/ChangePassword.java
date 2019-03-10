package by.panasyuk.controller.command;

import by.panasyuk.service.EmailService;
import by.panasyuk.service.user.PasswordService;
import by.panasyuk.service.exception.ArgumentCorrectException;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class ChangePassword implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        PasswordService passwordService = PasswordService.getInstance();
        try {
            String newPassword = passwordService.changePassword(login,email);
            EmailService sender = new EmailService();
            sender.send("your new password", "there is your new password: " + newPassword, email);
            request.setAttribute("route", Router.Type.REDIRECT);
        return PathManager.getProperty("redirect.login");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        catch (ArgumentCorrectException e) {
            request.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.forgot-password") +"&error=incorrectData";
        }
    }
}
