package by.panasyuk.controller.command;

import by.panasyuk.domain.Drug;
import by.panasyuk.service.drug.CrudDrugService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetDrugList implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        CrudDrugService service = new CrudDrugService();
        try {
            List<Drug> listDrugs = service.getAll();
            request.setAttribute("drugList",listDrugs);
            return  "/WEB-INF/views/admin.jsp";
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
