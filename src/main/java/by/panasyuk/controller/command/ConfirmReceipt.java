package by.panasyuk.controller.command;

import by.panasyuk.domain.Order;
import by.panasyuk.domain.User;
import by.panasyuk.service.OrderService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ConfirmReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String parameter = request.getParameter("id");
        User user = (User)session.getAttribute("user");
        int orderId = Integer.parseInt(parameter);
        OrderService service = new OrderService();
        try {
            Order order = service.getOrderById(orderId);
            service.confirm(order);
            List<Order> orderList = service.getAllUserOrders(user.getId());
            request.setAttribute("orderList",orderList);
            request.setAttribute("route", Router.Type.FORWARD);
            return PathManager.getProperty("forward.order-element");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
