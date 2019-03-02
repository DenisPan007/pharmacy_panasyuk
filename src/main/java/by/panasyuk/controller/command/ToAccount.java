package by.panasyuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToAccount implements Command {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        String role = (String)session.getAttribute("role");
        switch (role) {
            case "admin":
                req.setAttribute("route", Router.Type.REDIRECT);
                return "/start?command=toAdmin";
            case "client":
                req.setAttribute("route", Router.Type.FORWARD);
                return "/WEB-INF/views/account.jsp";
            case "guest":
                req.setAttribute("route", Router.Type.FORWARD);
                return "/start?command=toLogin";
            default:
                throw new CommandException();
        }
    }
}
