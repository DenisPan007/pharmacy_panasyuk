package by.panasyuk.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ToStartPage implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.setAttribute("route", Router.Type.FORWARD);
        return "/WEB-INF/views/start_page.jsp";
    }
}
