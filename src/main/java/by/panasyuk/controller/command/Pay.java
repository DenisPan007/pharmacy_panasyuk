package by.panasyuk.controller.command;

import by.panasyuk.domain.Order;
import by.panasyuk.service.OrderService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Pay implements Command{
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String attribute = request.getParameter("orderId");
        if(attribute==null){
            return "error";
        }
        int orderId = Integer.parseInt(attribute);
        OrderService service = new OrderService();
        try {
            Order order = service.getOrderById(orderId);
            service.pay(order);
            request.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.account");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
