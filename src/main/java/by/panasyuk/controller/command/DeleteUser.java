package by.panasyuk.controller.command;


import by.panasyuk.service.user.CrudUserService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUser implements Command {
    public String execute(HttpServletRequest request) throws CommandException {
        CrudUserService crudUserService = CrudUserService.getInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            crudUserService.delete(id);
            return "User has been deleted";
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
