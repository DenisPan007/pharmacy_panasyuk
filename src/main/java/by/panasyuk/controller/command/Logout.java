package by.panasyuk.controller.command;

import org.hsqldb.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout implements Command {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.getSession(false);
        req.setAttribute("route", Router.Type.REDIRECT);
        return "/start?command=toStartPage";
    }
}
