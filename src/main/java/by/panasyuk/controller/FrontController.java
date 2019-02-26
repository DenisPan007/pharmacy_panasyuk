package by.panasyuk.controller;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandException;
import by.panasyuk.controller.command.CommandProvider;
import by.panasyuk.dto.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/start")
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //processRequest(request, response);
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandString =request.getParameter("command");
        Command command = CommandProvider.getInstance().takeCommand(commandString);
        String path = null;
        try {
            path = command.execute(request);
        } catch (CommandException e) {
            request.setAttribute("error", e.getStackTrace());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
        request.getRequestDispatcher(path).forward(request, response);

        // Provide your code here

    }
}
