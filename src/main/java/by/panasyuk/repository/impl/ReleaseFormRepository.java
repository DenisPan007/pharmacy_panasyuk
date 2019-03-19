package by.panasyuk.repository.impl;

import by.panasyuk.domain.ReleaseForm;
import by.panasyuk.repository.AbstractJdbcRepository;
import by.panasyuk.repository.AutoConnection;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.Specification;

import java.sql.SQLException;
import java.util.List;

public class ReleaseFormRepository extends AbstractJdbcRepository<ReleaseForm,Integer> implements Repository<ReleaseForm,Integer> {
    @AutoConnection
    @Override
    public ReleaseForm add(ReleaseForm object) throws RepositoryException {
        return null;
    }
    @AutoConnection
    @Override
    public void update(ReleaseForm object) throws RepositoryException {

    }
    @AutoConnection
    @Override
    public void delete(ReleaseForm object) throws RepositoryException {

    }
    @AutoConnection
    @Override
    public List<ReleaseForm> getQuery(ReleaseForm obj, Specification<ReleaseForm> spec) throws RepositoryException {
        try {
            return spec.get(obj, connection);

        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
}
