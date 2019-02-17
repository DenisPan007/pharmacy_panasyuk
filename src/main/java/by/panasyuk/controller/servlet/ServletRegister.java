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
@WebServlet("/register")
public class ServletRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserService userService = UserService.getInstance();
        try {
            User user = userService.signUp(login,password);
            if (user != null){
                req.getRequestDispatcher("/mypage.jsp").forward(req,resp);
            }
        } catch (ServiceException e) {
            resp.sendError(600);
        }
        req.getRequestDispatcher("/mypage.jsp").forward(req,resp);

    }
}
