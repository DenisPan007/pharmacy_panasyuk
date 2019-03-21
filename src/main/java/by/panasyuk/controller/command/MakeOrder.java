package by.panasyuk.controller.command;

import by.panasyuk.domain.Item;
import by.panasyuk.domain.Order;
import by.panasyuk.domain.User;
import by.panasyuk.service.OrderService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.util.CookieFinder;
import by.panasyuk.util.PathManager;
import by.panasyuk.util.RoleEnum;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.List;

public class MakeOrder implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        RoleEnum role = (RoleEnum)session.getAttribute("role");
        if (role.equals(RoleEnum.GUEST)){
            request.setAttribute("route", Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.initial");
        }
        Cookie[] cookies = request.getCookies();
        User user = (User)session.getAttribute("user");
        String cookie = CookieFinder.getValueByName("cart", cookies).orElse(null);
        try {
            if (cookie == null) {
                request.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.initial");
            }
               cookie = URLDecoder.decode(cookie, "UTF-8");
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Item>>(){}.getType();
            try {
                List<Item> drugList = gson.fromJson(cookie, listType);
                OrderService service = new OrderService();
                Order order = new Order();
                order.setItemList(drugList);
                order.setStatus(Order.Status.NEW.name());
                order.setUserId(user.getId());
                int price = drugList.stream().mapToInt((details -> {return details.getAmount()*details.getDrug().getPrice();})).sum();
                order.setPrice(price);
                Order resultOrder = service.addOrder(order);
                session.setAttribute("order",resultOrder);
                session.setAttribute("clearCookie",true);
                request.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.order");

            }catch (JsonSyntaxException e){
                request.setAttribute("route", Router.Type.REDIRECT);
                return PathManager.getProperty("redirect.initial");
            }
        } catch (UnsupportedEncodingException |ServiceException e) {
            throw new CommandException(e);
        }
    }
}
