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
        int drugId = Integer.parseInt(request.getParameter("drugId"));
        PrescriptionService prescriptionService = new PrescriptionService();
        DoctorSelectorForPrescriptionService selector = new RandomDoctorSelectorService();
        try {
            if (prescriptionService.isUserHasPrescription(user.getId(),drugId)){
                return "bad";
            }
            User doctor = selector.select();
            prescriptionService.requestPrescription(user.getId(),drugId,doctor.getId());
            return "good";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
