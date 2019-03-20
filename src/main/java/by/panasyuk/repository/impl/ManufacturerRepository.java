package by.panasyuk.repository.impl;

import by.panasyuk.domain.Manufacturer;
import by.panasyuk.repository.AbstractJdbcRepository;
import by.panasyuk.repository.AutoConnection;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.Specification;

import java.sql.SQLException;
import java.util.List;

public class ManufacturerRepository extends AbstractJdbcRepository<Manufacturer,Integer> implements Repository<Manufacturer,Integer> {
    @AutoConnection
    @Override
    public Manufacturer add(Manufacturer object) throws RepositoryException {
        return null;
    }
    @AutoConnection
    @Override
    public void update(Manufacturer object) throws RepositoryException {

    }
    @AutoConnection
    @Override
    public void delete(Manufacturer object) throws RepositoryException {

    }
}
