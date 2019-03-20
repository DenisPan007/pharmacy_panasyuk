package by.panasyuk.controller.command;

import by.panasyuk.domain.Order;
import by.panasyuk.service.OrderService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class ConfirmReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String parameter = request.getParameter("id");
        int orderId = Integer.parseInt(parameter);
        OrderService service = new OrderService();
        try {
            Order order = service.getOrderById(orderId);
            service.confirm(order);
            return "true";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
