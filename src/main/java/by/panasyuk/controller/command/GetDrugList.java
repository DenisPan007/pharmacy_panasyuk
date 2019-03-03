package by.panasyuk.controller.command;

import by.panasyuk.domain.Drug;
import by.panasyuk.service.drug.CrudDrugService;
import by.panasyuk.service.exception.ServiceException;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetDrugList implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        CrudDrugService drugService = new CrudDrugService();
        try {
            List<Drug> drugList = drugService.getAll();
            List<String> drugNameList = drugList.stream()
                    .map(Drug::getName)
                    .collect(Collectors.toList());
            Gson gson = new Gson();
            String jasonList = gson.toJson(drugNameList);
            return jasonList;
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}

