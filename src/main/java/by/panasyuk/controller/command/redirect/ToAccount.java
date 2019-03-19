package by.panasyuk.controller.command.redirect;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandException;
import by.panasyuk.domain.Order;
import by.panasyuk.domain.Prescription;
import by.panasyuk.domain.User;
import by.panasyuk.service.OrderService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.service.prescription.PrescriptionService;
import by.panasyuk.util.RoleEnum;
import by.panasyuk.controller.command.Router;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAccount implements Command,RedirectCommand{
    private HttpSession session;
    private void userDataPrepare(HttpServletRequest request) throws CommandException {
        OrderService service = new OrderService();
        User user = (User)session.getAttribute("user");
        List<Order> orderList = null;
        try {
            orderList = service.getAllUserOrders(user.getId());
            request.setAttribute("orderList",orderList);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
    private void doctorDataPrepare(HttpServletRequest request) throws CommandException {
        PrescriptionService service = new PrescriptionService();
        User user = (User)session.getAttribute("user");
        List<Prescription> prescriptionList = null;
        try {
            prescriptionList = service.getAllDoctorPrescriptions(user.getId());
            request.setAttribute("prescriptionList",prescriptionList);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        session = request.getSession();
        RoleEnum role = (RoleEnum)session.getAttribute("role");
        switch (role) {
            case ADMIN:
                request.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.admin");
            case DOCTOR:
               doctorDataPrepare(request);
                request.setAttribute("route", Router.Type.FORWARD);
                return PathManager.getProperty("forward.doctor");
            case CLIENT:
                userDataPrepare(request);
                request.setAttribute("route", Router.Type.FORWARD);
                return PathManager.getProperty("forward.account");
            case GUEST:
                request.setAttribute("route", Router.Type.FORWARD);
                return PathManager.getProperty("forward.login");
            default:
                throw new CommandException();
        }
    }
}
