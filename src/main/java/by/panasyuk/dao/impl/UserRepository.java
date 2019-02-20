package by.panasyuk.dao.impl;

import by.panasyuk.dao.AbstractJdbcRepository;
import by.panasyuk.dao.AutoConnection;
import by.panasyuk.dao.Repository;
import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.dao.specification.Specification;
import by.panasyuk.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Example User DAO implementation
 */
public class UserRepository extends AbstractJdbcRepository<User, Integer> implements Repository<User, Integer> {
    @Override
    @AutoConnection
    public User add(User user) throws DaoException {
        String query = "INSERT INTO user (login,password,firstname,lastname,email,role) " +
                "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstname());
            ps.setString(4, user.getLastname());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getRole().name());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = (keys.getInt(1));
            user.setId(id);
            return user;
        } catch (SQLException e) {
            throw new DaoException("prepared statement failed", e);
        }
    }

    @Override
    @AutoConnection
    public void update(User object) throws DaoException {

        // Write your code here

        throw new UnsupportedOperationException();
    }

    @Override
    @AutoConnection
    public void delete(User object) throws DaoException {

        // Write your code here

        throw new UnsupportedOperationException();
    }

    @AutoConnection
    @Override
    public List<User> getQuery(User user, Specification<User> spec) throws DaoException {
        try {
            ResultSet resultSet = spec.get(user, connection);
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User resultUser = new User();
                resultUser.setId(resultSet.getInt(1));
                resultUser.setLogin(resultSet.getString(2));
                resultUser.setPassword(resultSet.getString(3));
                resultUser.setFirstname(resultSet.getString(4));
                resultUser.setLastname(resultSet.getString(5));
                resultUser.setEmail(resultSet.getString(6));
                resultUser.setRole(resultSet.getString(7));
                userList.add(resultUser);
            }
            return userList;
        } catch (SQLException e) {
            throw new DaoException("prepared statement failed", e);
        }
    }
}
