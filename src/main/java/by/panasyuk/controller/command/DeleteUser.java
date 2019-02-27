package by.panasyuk.controller.command;


import by.panasyuk.service.user.CrudService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DeleteUser implements Command {
    public String execute(HttpServletRequest request) throws CommandException {
        CrudService crudService = CrudService.getInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            crudService.delete(id);
            return "User has been deleted";
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
