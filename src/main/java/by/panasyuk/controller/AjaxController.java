package by.panasyuk.controller;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandException;
import by.panasyuk.controller.command.CommandProvider;
import by.panasyuk.controller.command.DeleteUser;
import by.panasyuk.dto.ResponseContent;
import by.panasyuk.service.UserService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax")
public class AjaxController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandString = request.getParameter("command");
        Command command = CommandProvider.getInstance().takeCommand(commandString);
        String text;
        try {
            text = command.execute(request);
            response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(text);
        } catch (CommandException e) {
            e.printStackTrace();
        }

    }
}
