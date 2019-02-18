package by.panasyuk.dao.specification;

import by.panasyuk.dao.AbstractJdbcDao;
import by.panasyuk.dao.GenericDao;
import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.dao.impl.JdbcDaoFactory;
import by.panasyuk.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GetByEmailTest {
    private static final String JDBCDRIVER_CLASS = "org.hsqldb.jdbc.JDBCDriver";
    private static final String DB_URL = "jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private Connection connection;
    private Statement statement;
    @Before
    public void connect()throws SQLException, ClassNotFoundException{
        Class.forName(JDBCDRIVER_CLASS);
        connection = DriverManager.getConnection(DB_URL, DB_USER, null);
        statement = connection.createStatement();
        statement.executeQuery("CREATE TABLE IF NOT EXISTS user (\n" +
                "  id int IDENTITY PRIMARY KEY, \n" +
                "  login varchar(45),\n" +
                "  password char(64),\n" +
                "  firstname varchar(45) ,\n" +
                "  lastname varchar(45) ,\n" +
                "  email varchar(45) ,\n" +
                "  role varchar(45))");

    }

    @Test
    public void get() throws SQLException, DaoException {String password1 = "5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5";
        statement.executeQuery(" INSERT INTO user (login,password,firstname,lastname,email,role) " +
                "VALUES('den1',\'" + password1 + "\'," +
                "'Denis','Panasyuk','@mail','client')");

        JdbcDaoFactory factory = JdbcDaoFactory.getInstance();
        GenericDao<User, Integer> userDao = factory.getTransactionalDao(User.class);
        AbstractJdbcDao<User,Integer> abstrDao = (AbstractJdbcDao<User,Integer>) userDao;
        abstrDao.setConnection(connection);
        User user = new User(0, "den1", password1, "Denis", "Panasyuk", "@mail", "client");
        Specification<User> spec = new GetByEmail();
        List<User> expectedList = new ArrayList<>(Arrays.asList(user));
        List<User> actualList = userDao.getQuery(user,spec);
        assertEquals(expectedList, actualList);
    }
}