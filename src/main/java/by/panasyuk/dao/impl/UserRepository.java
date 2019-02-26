package by.panasyuk.dao.impl;

import by.panasyuk.dao.AbstractJdbcRepository;
import by.panasyuk.dao.Repository;
import by.panasyuk.dao.exception.RepositoryException;
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
    public User add(User user) throws RepositoryException {
        String query = "INSERT INTO user (login,password,firstname,lastname,email,role) " +
                "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getRole());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = (keys.getInt(1));
            user.setId(id);
            return user;
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }

    @Override
    public void update(User user) throws RepositoryException {

        String query = "UPDATE user " +
                "SET login=?, password=?, firstname=?, lastname=?, email=?, role=?" +
                "WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getRole());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }

    @Override
    public void delete(User user) throws RepositoryException {

        String query = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }

        @Override
        public List<User> getQuery (User user, Specification < User > spec) throws RepositoryException {
            try {
                ResultSet resultSet = spec.get(user, connection);
                List<User> userList = new ArrayList<>();
                while (resultSet.next()) {
                    User resultUser = new User();
                    resultUser.setId(resultSet.getInt(1));
                    resultUser.setLogin(resultSet.getString(2));
                    resultUser.setPassword(resultSet.getString(3));
                    resultUser.setFirstName(resultSet.getString(4));
                    resultUser.setLastName(resultSet.getString(5));
                    resultUser.setEmail(resultSet.getString(6));
                    resultUser.setRole(resultSet.getString(7));
                    userList.add(resultUser);
                }
                return userList;
            } catch (SQLException e) {
                throw new RepositoryException("prepared statement failed", e);
            }
        }
    }
