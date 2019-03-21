package by.panasyuk.controller;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandException;
import by.panasyuk.controller.command.CommandProvider;

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
        try {
            Command command = CommandProvider.getInstance().takeCommand(commandString);
            String text = command.execute(request);
            response.setContentType("text/plain");
            response.getWriter().write(text);
        } catch (CommandException e) {
            response.getWriter().write(e.getMessage());
            response.setStatus(406);
        }
        catch (Exception e) {
            response.getWriter().write("something going wrong...sorry");
            response.setStatus(500);
        }

    }
}
