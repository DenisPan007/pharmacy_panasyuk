package by.panasyuk.controller;

import by.panasyuk.controller.command.Command;
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
        req.getRequestDispatcher("/WEB-INF/views/start_page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandProvider.getInstance().takeCommand("CommandExample");
        ResponseContent responseContent = command.execute(request);

        // Provide your code here

    }
}
