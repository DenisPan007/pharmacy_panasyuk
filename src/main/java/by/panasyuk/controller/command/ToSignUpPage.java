package by.panasyuk.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ToSignUpPage implements Command {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        String errorType = req.getParameter("error");
        req.setAttribute("error",errorType);
        req.setAttribute("route",Router.Type.FORWARD);
        return  "/WEB-INF/views/sign_up.jsp";
    }
}
