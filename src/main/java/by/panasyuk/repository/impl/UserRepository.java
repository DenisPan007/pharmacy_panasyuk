package by.panasyuk.repository.impl;

import by.panasyuk.domain.User;
import by.panasyuk.repository.AbstractJdbcRepository;
import by.panasyuk.repository.AutoConnection;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * User Repository implementation
 */
public class UserRepository extends AbstractJdbcRepository<User, Integer> implements Repository<User, Integer> {
    @AutoConnection
    @Override
    public User add(User user) throws RepositoryException {
        String query = "INSERT INTO user (login,password,firstname,lastname,email,role) " +
                "VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
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
    @AutoConnection
    @Override
    public void update(User user) throws RepositoryException {

        String query = "UPDATE user " +
                "SET login=?, password=?, firstname=?, lastname=?, email=?, role=?" +
                "WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)){
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
    @AutoConnection
    @Override
    public void delete(User user) throws RepositoryException {

        String query = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }
    @AutoConnection
        @Override
        public List<User> getQuery (User user, Specification < User > spec) throws RepositoryException {
            try {
                return spec.get(user, connection);

            } catch (SQLException e) {
                throw new RepositoryException("prepared statement failed", e);
            }
        }
    }
