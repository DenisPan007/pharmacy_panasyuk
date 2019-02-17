package by.panasyuk.dao.impl;

import by.panasyuk.dao.AbstractJdbcDao;
import by.panasyuk.dao.GenericDao;
import by.panasyuk.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example User DAO implementation
 */
public class UserDaoImpl extends AbstractJdbcDao<User, Integer> implements GenericDao<User, Integer> {
    @Override
    protected User setGeneratedKey(ResultSet keys, User object) throws SQLException {
        keys.next();
        int id = (keys.getInt(1));
        object.setId((int) id);
        return object;
    }

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

    protected void prepareStatementForCreate(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1, object.getLogin());
        statement.setString(2, object.getPassword());
        statement.setString(3, object.getFirstname());
        statement.setString(4, object.getLastname());
        statement.setString(5, object.getEmail());
        statement.setString(6, object.getRole().name());

    }

    @Override
    public String getSelectQuery() {

        return "SELECT * FROM user WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {

        return "INSERT INTO user (login,password,firstname,lastname,email,role) " +
                "VALUES(?,?,?,?,?,?)";

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
}
