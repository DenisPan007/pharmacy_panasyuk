package by.panasyuk.service;

import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.UserRepositoryTest;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;
import by.panasyuk.service.user.UserPresentChecker;
import by.panasyuk.service.user.SignUpService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.Assert.*;

public class UserServiceTest {
    @BeforeClass
    public static void connect() throws SQLException, ClassNotFoundException, IOException, RepositoryException {
        InputStream inputStream = UserRepositoryTest.class.getResourceAsStream("/db.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        statement.executeQuery("CREATE TABLE IF NOT EXISTS user (\n" +
                "  id int IDENTITY PRIMARY KEY, \n" +
                "  login varchar(45),\n" +
                "  password varchar (45),\n" +
                "  firstname varchar(45) ,\n" +
                "  lastname varchar(45) ,\n" +
                "  email varchar(45) ,\n" +
                "  role varchar(45),\n" +
                "  address varchar(45))");
        statement.executeQuery(" INSERT INTO user (login,password,firstname,lastname,email,role,address) " +
                "VALUES('admin','passwordTest','Denis','Panasyuk','@email','client','address')");
        statement.executeQuery(" INSERT INTO user (login,role,email) " +
                "VALUES('den2','client','@mail')");
    }
    @Test
    public void isReservedByLogin()throws ServiceException {
        String login = "admin";
        UserPresentChecker presentChecker = new UserPresentChecker();
        Boolean result = presentChecker.isReservedLogin(login);
        assertTrue(result);
    }
    @Test
    public void isReservedByEmail()throws ServiceException {
        String email = "@mail";
        UserPresentChecker presentChecker = new UserPresentChecker();
        Boolean result = presentChecker.isReservedEmail(email);
        assertTrue(result);
    }

    @Test
    public void signUp() throws ServiceException {
        String login = "login";
        String password = "password";
        String email = "@mail";
        String firstName = "name";
        String lastName = "lastName";
        String address = "adress";
        User expectedUser = new User(login,password,email,firstName,lastName,address);
        expectedUser.setId(2);
        SignUpService signUpService = new SignUpService();
            User actualUser = signUpService.signUp(login,password,email,firstName,lastName,address);
            assertEquals(expectedUser,actualUser);
    }
}