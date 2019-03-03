package by.panasyuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToAccount implements Command {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        RoleEnum role = (RoleEnum)session.getAttribute("role");
        switch (role) {
            case ADMIN:
                req.setAttribute("route", Router.Type.REDIRECT);
                return "/start?command=toAdmin";
            case CLIENT:
                req.setAttribute("route", Router.Type.FORWARD);
                return "/WEB-INF/views/account.jsp";
            case GUEST:
                req.setAttribute("route", Router.Type.FORWARD);
                return "/start?command=toLogin";
            default:
                throw new CommandException();
        }
    }
}
