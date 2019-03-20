package by.panasyuk.repository.impl;

import by.panasyuk.domain.Manufacturer;
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

public class ManufacturerRepository extends AbstractJdbcRepository<Manufacturer,Integer> implements Repository<Manufacturer,Integer> {
    @AutoConnection
    @Override
    public Manufacturer add(Manufacturer manufacturer) throws RepositoryException {
        String query = "INSERT INTO manufacturer (name) VALUES(?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, manufacturer.getName());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = (keys.getInt(1));
            manufacturer.setId(id);
            return manufacturer;
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
    @AutoConnection
    @Override
    public void update(Manufacturer manufacturer) throws RepositoryException {
        String query = "UPDATE manufacturer SET name=? where id=?";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, manufacturer.getName());
            ps.setInt(2, manufacturer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
    @AutoConnection
    @Override
    public void delete(Manufacturer manufacturer) throws RepositoryException {
        String query = "DELETE FROM manufacturer WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, manufacturer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }
}
