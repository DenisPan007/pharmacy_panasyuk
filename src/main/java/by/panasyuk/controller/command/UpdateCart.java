package by.panasyuk.controller.command;

import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class UpdateCart implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.setAttribute("route", Router.Type.FORWARD);
        return PathManager.getProperty("forward.cart-element");
    }
}
