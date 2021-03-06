package by.panasyuk.controller.command;


import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.service.user.CrudUserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUser implements Command {
    public String execute(HttpServletRequest request) throws CommandException {
        CrudUserService crudUserService = new CrudUserService();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            crudUserService.delete(id);
            return "User has been deleted";
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
