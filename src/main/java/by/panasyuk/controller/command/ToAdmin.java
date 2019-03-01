package by.panasyuk.controller.command;

import by.panasyuk.domain.Drug;
import by.panasyuk.domain.User;
import by.panasyuk.service.drug.CrudDrugService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.service.user.CrudUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToAdmin implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        CrudDrugService drugService = new CrudDrugService();
        CrudUserService userService = new CrudUserService();
        try {
            List<Drug> drugList = drugService.getAll();
            List<User> userList = userService.getAll();
            request.setAttribute("drugList",drugList);
            request.setAttribute("userList",userList);
            request.setAttribute("route",Router.Type.FORWARD);
            return  "/WEB-INF/views/admin.jsp";
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
