package by.panasyuk.controller.command.redirect;

import by.panasyuk.controller.command.Command;
import by.panasyuk.controller.command.CommandException;
import by.panasyuk.controller.command.Router;
import by.panasyuk.domain.Order;
import by.panasyuk.service.OrderService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class ToOrder implements Command, RedirectCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String parameter = request.getParameter("orderId");
        if(parameter != null) {
            int orderId = Integer.parseInt(parameter);
            OrderService service = new OrderService();
            try {
                Order order = service.getOrderById(orderId);
                request.getSession().setAttribute("order",order);
            } catch (ServiceException e) {
                throw new CommandException();
            }
        }
        request.setAttribute("route", Router.Type.FORWARD);
        return PathManager.getProperty("forward.order");
    }
}
