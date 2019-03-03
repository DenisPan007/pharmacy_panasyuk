package by.panasyuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToLogin implements Command {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        if (session.getAttribute("role")!="guest"){
            req.setAttribute("route", Router.Type.REDIRECT);
            return "/start?command=toStartPage";
        }
        String errorType = req.getParameter("error");
        req.setAttribute("error",errorType);
        req.setAttribute("route",Router.Type.FORWARD);
        return  "/WEB-INF/views/login.jsp";
    }
}