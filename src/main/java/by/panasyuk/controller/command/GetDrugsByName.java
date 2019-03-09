package by.panasyuk.controller.command;

import by.panasyuk.domain.Drug;
import by.panasyuk.service.drug.CrudDrugService;
import by.panasyuk.service.exception.ServiceException;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetDrugsByName implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String name = request.getParameter("name");
        CrudDrugService drugService = new CrudDrugService();
        try {
            List<Drug> drugList = drugService.getByName(name);
            Gson gson = new Gson();
            return gson.toJson(drugList);
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
