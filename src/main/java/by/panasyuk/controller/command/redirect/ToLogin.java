package by.panasyuk.controller.command.redirect;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandException;
import by.panasyuk.util.RoleEnum;
import by.panasyuk.controller.command.Router;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToLogin implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        RoleEnum role = (RoleEnum)session.getAttribute("role");
        if (!role.equals(RoleEnum.GUEST)){
            request.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.initial");
        }
        String errorType = request.getParameter("error");
        request.setAttribute("error",errorType);
        request.setAttribute("route",Router.Type.FORWARD);
        return PathManager.getProperty("forward.login");
    }
}
