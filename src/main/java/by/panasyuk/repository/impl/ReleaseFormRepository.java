package by.panasyuk.repository.impl;

import by.panasyuk.domain.ReleaseForm;
import by.panasyuk.repository.AbstractJdbcRepository;
import by.panasyuk.repository.AutoConnection;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.exception.RepositoryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReleaseFormRepository extends AbstractJdbcRepository<ReleaseForm, Integer> implements Repository<ReleaseForm, Integer> {
    @AutoConnection
    @Override
    public ReleaseForm add(ReleaseForm releaseForm) throws RepositoryException {
        String query = "INSERT INTO release_form (name) VALUES(?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, releaseForm.getDescription());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = (keys.getInt(1));
            releaseForm.setId(id);
            return releaseForm;
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }

    @AutoConnection
    @Override
    public void update(ReleaseForm releaseForm) throws RepositoryException {
        String query = "UPDATE release_form SET name=? where id=?";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, releaseForm.getDescription());
            ps.setInt(2, releaseForm.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }

    @AutoConnection
    @Override
    public void delete(ReleaseForm releaseForm) throws RepositoryException {
        String query = "DELETE FROM release_form WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, releaseForm.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }

}
