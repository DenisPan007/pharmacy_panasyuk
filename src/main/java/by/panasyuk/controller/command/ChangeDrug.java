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

public class ChangeDrug implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("releaseForm");
        String manufacturerName = request.getParameter("manufacturer");
        String name = request.getParameter("name");
        String isPrescriptionRequired = request.getParameter("prescription.given");
        String price = request.getParameter("price");
        String availableAmount = request.getParameter("availableAmount");
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
            if (Integer.parseInt(availableAmount)<=0) {
                return "invalidPrice";
            }
        }catch (NumberFormatException e){
            return "invalidPrice";
        }
        try {
            ReleaseForm releaseForm = releaseFromService.getByDescription(description);
            Manufacturer manufacturer = manufacturerService.getByName(manufacturerName);
            Drug drug = new Drug(id,name, Boolean.valueOf(isPrescriptionRequired), Integer.parseInt(price), manufacturer, releaseForm, Integer.parseInt(availableAmount));
            drugService.update(drug);
            Gson gson = new Gson();
            return gson.toJson(drug);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
