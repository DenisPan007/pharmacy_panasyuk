package by.panasyuk.controller.command;

import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToLogin implements Command {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        RoleEnum role = (RoleEnum)session.getAttribute("role");
        if (!role.equals(RoleEnum.GUEST)){
            req.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.initial");
        }
        String errorType = req.getParameter("error");
        req.setAttribute("error",errorType);
        req.setAttribute("route",Router.Type.FORWARD);
        return PathManager.getProperty("forward.login");
    }
}
