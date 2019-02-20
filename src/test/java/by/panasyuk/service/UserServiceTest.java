package by.panasyuk.service;

import by.panasyuk.dao.AbstractJdbcRepository;
import by.panasyuk.dao.Repository;
import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.dao.impl.JdbcRepositoryFactory;
import by.panasyuk.dao.specification.UserRepositoryTest;
import by.panasyuk.domain.User;
import by.panasyuk.service.exception.ServiceException;
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
    public static void connect() throws SQLException, ClassNotFoundException, IOException, DaoException {
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
                "  role varchar(45))");
        statement.executeQuery(" INSERT INTO user (login,password,firstname,lastname,email,role) " +
                "VALUES('admin','passwordTest','Denis','Panasyuk','@email','client')");
        statement.executeQuery(" INSERT INTO user (login,role,email) " +
                "VALUES('den2','client','@mail')");
    }
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
        User expectedUser = new User(login,password,email);
        expectedUser.setId(2);
        UserService userService = UserService.getInstance();
            User actualUser = userService.signUp(login,password,email);
            assertEquals(expectedUser,actualUser);
    }
}