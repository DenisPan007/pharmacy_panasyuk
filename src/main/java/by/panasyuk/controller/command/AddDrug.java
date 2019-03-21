package by.panasyuk.controller.command;

import by.panasyuk.domain.Drug;
import by.panasyuk.domain.Manufacturer;
import by.panasyuk.domain.ReleaseForm;
import by.panasyuk.service.ManufacturerService;
import by.panasyuk.service.ReleaseFromService;
import by.panasyuk.service.drug.CrudDrugService;
import by.panasyuk.service.exception.ServiceException;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

public class AddDrug implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String description = request.getParameter("releaseForm");
        String manufacturerName = request.getParameter("manufacturer");
        String name = request.getParameter("name");
        String isPrescriptionRequired = request.getParameter("prescription");
        String price = request.getParameter("price");
        CrudDrugService drugService = new CrudDrugService();
        ManufacturerService manufacturerService = new ManufacturerService();
        ReleaseFromService releaseFromService = new ReleaseFromService();
        if (name.equals("")){
            return "emptyName";
        }
        try {
            if (Integer.parseInt(price)<=0) {
                return "invalidPrice";
            }
        }catch (NumberFormatException e){
            return "invalidPrice";
        }
        try {
            ReleaseForm releaseForm = releaseFromService.getByDescription(description);
            Manufacturer manufacturer = manufacturerService.getByName(manufacturerName);
            Drug drug = drugService.add(name, Boolean.valueOf(isPrescriptionRequired), Integer.parseInt(price), manufacturer, releaseForm);
            Gson gson = new Gson();
            return gson.toJson(drug);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
