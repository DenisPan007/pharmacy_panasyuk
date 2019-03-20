package by.panasyuk.repository.impl;

import by.panasyuk.domain.Order;
import by.panasyuk.repository.AbstractJdbcRepository;
import by.panasyuk.repository.AutoConnection;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.exception.RepositoryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderRepository extends AbstractJdbcRepository<Order, Integer> implements Repository<Order, Integer> {
    @AutoConnection
    @Override
    public Order add(Order order) throws RepositoryException {
        String query = "INSERT INTO drug_order (user_id,status,price) " +
                "VALUES(?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, order.getUserId());
            ps.setString(2, order.getStatus());
            ps.setInt(3, order.getPrice());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = (keys.getInt(1));
            order.setId(id);
            return order;
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }

    @AutoConnection
    @Override
    public void update(Order order) throws RepositoryException {
        String query = "UPDATE drug_order " +
                "SET user_id=?, status=?, price=? " +
                "WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, order.getUserId());
            ps.setString(2, order.getStatus());
            ps.setInt(3, order.getPrice());
            ps.setInt(4, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }

    @AutoConnection
    @Override
    public void delete(Order order) throws RepositoryException {
        String query = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }
}
