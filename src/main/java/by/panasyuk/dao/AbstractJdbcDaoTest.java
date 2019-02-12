package by.panasyuk.dao;

import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.dao.impl.UserDaoImpl;
import by.panasyuk.domain.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class AbstractJdbcDaoTest {
   // private static final Logger LOGGER = LogManager.getLogger(AbstractJdbcDaoTest.class);
    private static final String JDBCDRIVER_CLASS = "org.hsqldb.jdbc.JDBCDriver" ;
    private static final String DB_URL = "jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1" ;
    private static final String DB_USER = "sa" ;

    @Test
    public void getByPK() throws SQLException,ClassNotFoundException, DaoException {

        Class.forName(JDBCDRIVER_CLASS);
        Connection connection = DriverManager.getConnection(DB_URL,DB_USER,null);
        AbstractJdbcDao<User,Integer> userDao = new UserDaoImpl();
        Optional<User> user = userDao.getByPK(1);


    }
}