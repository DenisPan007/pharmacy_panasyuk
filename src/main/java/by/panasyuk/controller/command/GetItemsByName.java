package by.panasyuk.controller.command;

import by.panasyuk.domain.Drug;
import by.panasyuk.domain.Item;
import by.panasyuk.service.drug.CrudDrugService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class GetItemsByName implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String name = request.getParameter("name");
        CrudDrugService drugService = new CrudDrugService();
        try {
            List<Drug> drugList = drugService.getByName(name);
            List<Item> itemList = drugList.stream().map(Item::new).collect(Collectors.toList());
            request.setAttribute("itemList",itemList);
            request.setAttribute("route", Router.Type.FORWARD);
            return PathManager.getProperty("forward.selected-items");
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}