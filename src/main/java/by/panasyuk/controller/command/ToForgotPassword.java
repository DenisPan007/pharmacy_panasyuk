package by.panasyuk.controller.command;

import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class ToForgotPassword implements Command,RedirectCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.setAttribute("route",Router.Type.FORWARD);
        return PathManager.getProperty("forward.forgot-password");
    }
}
