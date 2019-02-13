package by.panasyuk.dao.impl;

import by.panasyuk.dao.GenericDao;
import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.dao.impl.JdbcDaoFactory;
import by.panasyuk.dao.impl.UserDaoImpl;
import by.panasyuk.domain.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static org.junit.Assert.*;

public class AbstractJdbcDaoTest {
    // private static final Logger LOGGER = LogManager.getLogger(AbstractJdbcDaoTest.class);
    private static final String JDBCDRIVER_CLASS = "org.hsqldb.jdbc.JDBCDriver";
    private static final String DB_URL = "jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";

    @Test
    public void getByPK() throws SQLException, ClassNotFoundException, DaoException {

        Class.forName(JDBCDRIVER_CLASS);
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, null);
        Statement statement = connection.createStatement();
        statement.executeQuery("CREATE TABLE user (\n" +
                "  id int, \n" +
                "  login varchar(45),\n" +
                "  password char(64),\n" +
                "  firstname varchar(45) ,\n" +
                "  lastname varchar(45) ,\n" +
                "  email varchar(45) ,\n" +
                "  role varchar(45))");
        String password1 = "5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5";
        statement.executeQuery(" INSERT INTO user (id, login,password,firstname,lastname,email,role) " +
                "VALUES('1','den1',\'" + password1 + "\'," +
                "'Denis','Panasyuk','@mail','client')");
        JdbcDaoFactory factory = JdbcDaoFactory.getInstance();
        GenericDao<User, Integer> userDao = factory.getTransactionalDao(User.class, connection);
        Optional<User> expectedUser = Optional.of(new User(1, "den1", password1, "Denis", "Panasyuk", "@mail", "client"));
        Optional<User> actualUser = userDao.getByPK(1);
        assertEquals(expectedUser, actualUser);
    }
}