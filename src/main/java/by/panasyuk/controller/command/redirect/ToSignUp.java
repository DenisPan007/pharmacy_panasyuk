package by.panasyuk.controller.command.redirect;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandException;
import by.panasyuk.controller.command.Router;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class ToSignUp implements Command,RedirectCommand {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        String errorType = req.getParameter("error");
        req.setAttribute("error",errorType);
        req.setAttribute("route", Router.Type.FORWARD);
        return PathManager.getProperty("forward.sign.up");
    }
}
