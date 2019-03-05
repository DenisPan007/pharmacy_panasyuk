package by.panasyuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoInitialRedirectCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Command initialCommand = (Command)session.getAttribute("initialCommand");
        if (initialCommand==null){
            initialCommand = new ToStartPage();
        }
        return initialCommand.execute(request);
    }
}
