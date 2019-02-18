package by.panasyuk.service;

import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserServiceTest {
    @Test
    public void isReservedByLogin()throws ServiceException {
        String login = "admin";
        UserService userService = UserService.getInstance();
        Boolean result = userService.isResevedLogin(login);
        assertTrue(result);
    }
    @Test
    public void isReservedByEmail()throws ServiceException {
        String email = "@mail";
        UserService userService = UserService.getInstance();
        Boolean result = userService.isResevedEmail(email);
        assertTrue(result);
    }

    @Test
    public void signUp() throws ServiceException {
        String login = "login";
        String password = "password";
        String email = "@mail";
        UserService userService = UserService.getInstance();
            //User user = userService.signUp(login,password,email);
    }
}