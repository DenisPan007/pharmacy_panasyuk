package by.panasyuk.dao.impl;

import by.panasyuk.dao.AbstractJdbcDao;
import by.panasyuk.dao.GenericDao;
import by.panasyuk.dao.exception.PersistException;
import by.panasyuk.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Example User DAO implementation
 */
public class UserDaoImpl extends AbstractJdbcDao<User, Integer> implements GenericDao<User, Integer> {

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setLogin(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setFirstname(rs.getString(4));
            user.setLastname(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setRole(rs.getString(7));
            userList.add(user);

        }

        return userList;
    }


    @Override
    protected void prepareStatementForGet(PreparedStatement statement, Integer key) throws SQLException {
        statement.setInt(1, key);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {

        //provide your code here

        throw new UnsupportedOperationException();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws SQLException {

        //provide your code here

        throw new UnsupportedOperationException();
    }

    @Override
    public String getSelectQuery() {

        return "SELECT * FROM user WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {

        //provide your code here

        throw new UnsupportedOperationException();
    }

    @Override
    public String getUpdateQuery() {

        //provide your code here

        throw new UnsupportedOperationException();
    }

    @Override
    public String getDeleteQuery() {

        //provide your code here

        throw new UnsupportedOperationException();
    }


    @Override
    public Optional<User> create() throws PersistException {
        //provide your code here

        throw new UnsupportedOperationException();
    }
}
