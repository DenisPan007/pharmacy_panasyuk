package by.panasyuk.controller.servlet;

import by.panasyuk.domain.User;
import by.panasyuk.service.UserService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import by.panasyuk.controller.command.Login;

@WebServlet("/register")
public class ServletRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        UserService userService = UserService.getInstance();
        try {
            password = Login.passwordHash(password);
            if (userService.isReservedLogin(login)) {
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            } else if (userService.isReservedEmail(email)) {
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            } else {
                User user = userService.signUp(login, password, email);
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/views/successful_registration.jsp").forward(req, resp);
            }
        } catch (ServiceException e) {
            resp.sendError(600);
        } catch (NoSuchAlgorithmException e) {
            resp.sendError(601);
        }
    }
}
