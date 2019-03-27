package by.panasyuk.controller.command;

import by.panasyuk.domain.User;
import by.panasyuk.service.prescription.DoctorSelectorForPrescriptionService;
import by.panasyuk.service.prescription.PrescriptionService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.service.prescription.RandomDoctorSelectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestPrescription implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        if(user == null){
            return "notLogined";
        }
        int drugId = Integer.parseInt(request.getParameter("drugId"));
        PrescriptionService prescriptionService = new PrescriptionService();
        DoctorSelectorForPrescriptionService selector = new RandomDoctorSelectorService();
        try {
            if (prescriptionService.isUserHasValidPrescription(user.getId(),drugId)){
                return "valid";
            }
            if (!prescriptionService.isUserHasInvalidPrescription(user.getId(),drugId)
                    && prescriptionService.isUserQuirePrescription(user.getId(),drugId)){
                return "alreadyHave";
            }
            User doctor = selector.select();
            prescriptionService.requestPrescription(user.getId(),drugId,doctor.getId());
            return "ok";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
