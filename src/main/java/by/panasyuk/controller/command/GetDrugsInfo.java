package by.panasyuk.controller.command;
import by.panasyuk.domain.Manufacturer;
import by.panasyuk.domain.ReleaseForm;
import by.panasyuk.service.ManufacturerService;
import by.panasyuk.service.ReleaseFromService;
import by.panasyuk.service.exception.ServiceException;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetDrugsInfo implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ManufacturerService manufacturerService = new ManufacturerService();
        ReleaseFromService releaseFromService = new ReleaseFromService();
        try {
            List<Manufacturer> manufacturerList = manufacturerService.getAllManufacturers();
        List<ReleaseForm> releaseFormList = releaseFromService.getAllReleaseForms();
        List<List> resultList = new ArrayList<>(Arrays.asList(releaseFormList,manufacturerList));
            Gson gson = new Gson();
            return gson.toJson(resultList);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
