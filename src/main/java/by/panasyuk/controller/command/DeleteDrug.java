package by.panasyuk.controller.command;

import by.panasyuk.service.drug.CrudDrugService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DeleteDrug implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        CrudDrugService crudService = CrudDrugService.getInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            crudService.delete(id);
            return "Drug has been deleted";
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
