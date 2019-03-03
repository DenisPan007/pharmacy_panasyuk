package by.panasyuk.controller.command;

import by.panasyuk.service.user.EmailService;
import by.panasyuk.service.user.PasswordService;
import by.panasyuk.service.exception.ArgumentCorrectException;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class ChangePassword implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        PasswordService passwordService = PasswordService.getInstance();
        try {
            String newPassword = passwordService.changePassword(login,email);
            EmailService sender = EmailService.getInstance();
            sender.send("your new password", "there is your new password: " + newPassword, email);
        return "/WEB-INF/views/login.jsp";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        catch (ArgumentCorrectException e) {
            request.setAttribute("error", "there is no such a user with this email");
            return "/WEB-INF/views/forgot_password.jsp";
        }
    }
}