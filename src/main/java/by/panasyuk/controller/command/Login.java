package by.panasyuk.controller.command;

import by.panasyuk.service.UserService;
import by.panasyuk.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login implements Command {
    public static String passwordHash(String password) throws NoSuchAlgorithmException{
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] passwordByte = sha256.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < passwordByte.length; i++){
            String s = Integer.toHexString(passwordByte[i]&0xff);
            if(s.length() == 1){
                s = "0" + s;
            }
            sb.append(s);
        }
        return sb.toString();
    }
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        UserService service = UserService.getInstance();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            password = passwordHash(password);
            if (!service.login(login,password)){
               // req.setAttribute("invalidLogin",true);
                return "/WEB-INF/views/login.jsp";
            }
            else{
                req.setAttribute("login",login);
                return "/WEB-INF/views/successfulLogin.jsp";
            }
        }catch (ServiceException | NoSuchAlgorithmException e){
            throw new CommandException(e);
        }

    }
}
