package by.panasyuk.controller;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandException;
import by.panasyuk.controller.command.Router;
import by.panasyuk.util.PathManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = {"/"})
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Command command = (Command) request.getAttribute("command");
            String path = command.execute(request);
            Router.Type type = (Router.Type) request.getAttribute("route");
            if (type.equals(Router.Type.FORWARD)) {
                request.getRequestDispatcher(path).forward(request, response);
            } else {
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + path);
            }
        } catch (CommandException e) {
            request.setAttribute("error", e.getMessage());
            response.setStatus(500);
            request.getRequestDispatcher(PathManager.getProperty("error-page")).forward(request, response);
        }
        catch (Exception e){
            request.setAttribute("error", "Something going wrong, go to start - page");
            response.setStatus(500);
            request.getRequestDispatcher(PathManager.getProperty("error-page")).forward(request, response);
        }
    }
}
