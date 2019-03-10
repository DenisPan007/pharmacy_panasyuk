package by.panasyuk.controller.command.redirect;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandException;
import by.panasyuk.controller.command.Router;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class ToForgotPassword implements Command,RedirectCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String errorType = request.getParameter("error");
        request.setAttribute("error",errorType);
        request.setAttribute("route", Router.Type.FORWARD);
        return PathManager.getProperty("forward.forgot-password");
    }
}
