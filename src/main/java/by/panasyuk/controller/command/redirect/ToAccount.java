package by.panasyuk.controller.command.redirect;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandException;
import by.panasyuk.util.RoleEnum;
import by.panasyuk.controller.command.Router;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToAccount implements Command,RedirectCommand{
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        RoleEnum role = (RoleEnum)session.getAttribute("role");
        switch (role) {
            case ADMIN:
                req.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.admin");
            case CLIENT:
                req.setAttribute("route", Router.Type.FORWARD);
                return PathManager.getProperty("forward.account");
            case GUEST:
                req.setAttribute("route", Router.Type.FORWARD);
                return PathManager.getProperty("forward.login");
            default:
                throw new CommandException();
        }
    }
}
