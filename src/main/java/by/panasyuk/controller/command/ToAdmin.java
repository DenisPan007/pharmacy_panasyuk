package by.panasyuk.controller.command;

import by.panasyuk.domain.Drug;
import by.panasyuk.domain.User;
import by.panasyuk.service.drug.CrudDrugService;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.service.user.CrudUserService;
import by.panasyuk.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAdmin implements Command,RedirectCommand {
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        HttpSession session = req.getSession();
        RoleEnum role = (RoleEnum)session.getAttribute("role");
        if (!role.equals(RoleEnum.ADMIN)){
            req.setAttribute("route",Router.Type.REDIRECT);
            return PathManager.getProperty("redirect.home");
        }
        CrudDrugService drugService = new CrudDrugService();
        CrudUserService userService = new CrudUserService();
        try {
            List<Drug> drugList = drugService.getAll();
            List<User> userList = userService.getAll();
            User admin = null;
            for (User user:userList) {
                if (user.getRole().equalsIgnoreCase("admin")){
                    admin = user;
                }
            }
            if (admin!=null){
                userList.remove(admin);
            }
            req.setAttribute("drugList",drugList);
            req.setAttribute("userList",userList);
            req.setAttribute("route",Router.Type.FORWARD);
            return PathManager.getProperty("forward.admin");
        } catch (ServiceException e) {
            throw new CommandException();
        }
    }
}
