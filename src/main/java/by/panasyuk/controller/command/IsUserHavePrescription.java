package by.panasyuk.controller.command;

import by.panasyuk.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class IsUserHavePrescription implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
       HttpSession session =  request.getSession();
       Object attribute = session.getAttribute("user");
       if(attribute!=null) {
           User user = (User) attribute;
           return "true";
       }
        else{
            return "false";
       }
    }
}
