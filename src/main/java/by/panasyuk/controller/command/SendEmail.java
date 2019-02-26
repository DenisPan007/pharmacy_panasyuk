package by.panasyuk.controller.command;

import by.panasyuk.service.EmailSender;
import by.panasyuk.service.UserService;
import by.panasyuk.service.exception.ServiceException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class SendEmail implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        UserService service = UserService.getInstance();
        try {
        if (!service.isReservedLogin(login)||!service.changePassword(login,email)) {
            request.setAttribute("error", "there is no such a user with this email");
            return "/WEB-INF/views/forgot_password.jsp";
        }
        return "/WEB-INF/views/login.jsp";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
