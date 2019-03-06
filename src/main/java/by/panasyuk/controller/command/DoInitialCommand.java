package by.panasyuk.controller.command;

import by.panasyuk.controller.command.redirect.ToHome;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoInitialCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Command initialCommand = (Command)session.getAttribute("initialCommand");
        if (initialCommand==null){
            initialCommand = new ToHome();
        }
        return initialCommand.execute(request);
    }
}
