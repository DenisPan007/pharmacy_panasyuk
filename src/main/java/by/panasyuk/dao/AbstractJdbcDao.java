package by.panasyuk.dao;

import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.dao.specification.Specification;

import java.sql.*;
import java.util.List;

/**
 * Abstract JDBC DAO
 *
 * @param <T>  - Identified entity
 * @param <PK> - Type primary key of entity
 */
public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Number> implements Repository<T, PK> {
    protected Connection connection;

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract T setGeneratedKey(ResultSet keys, T object) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForGet(PreparedStatement statement, PK key) throws SQLException;

    protected abstract void prepareStatementForCreate(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    @AutoConnection
    public T add(T object) throws DaoException {
        String query = getCreateQuery();
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepareStatementForCreate(ps, object);
            int rows = ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            return setGeneratedKey(keys, object);
        } catch (SQLException e) {
            throw new DaoException("prepared statement failed", e);
        }
    }

    @Override
    @AutoConnection
    public void update(T object) throws DaoException {

        // Write your code here

        throw new UnsupportedOperationException();
    }

    @Override
    @AutoConnection
    public void delete(T object) throws DaoException {

        // Write your code here

        throw new UnsupportedOperationException();
    }

    @AutoConnection
    @Override
    public List<T> getQuery(T object, Specification<T> spec) throws DaoException {
        try {
            ResultSet resultSet = spec.get(object, connection);
            return parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException("prepared statement failed", e);
        }
    }
}
