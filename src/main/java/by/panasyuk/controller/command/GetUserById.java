package by.panasyuk.controller.command;

import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.service.user.CrudUserService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUserById implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String idStr = request.getParameter("id");
        if (idStr==null){
            throw new IllegalArgumentException();
        }
        int id = Integer.parseInt(idStr);
        CrudUserService userService = new CrudUserService();
        try {
            User user = userService.getById(id);
            Gson gson = new Gson();
            return gson.toJson(user);
        } catch (ServiceException |IllegalArgumentException e) {
            throw new CommandException();
        }
    }
}
