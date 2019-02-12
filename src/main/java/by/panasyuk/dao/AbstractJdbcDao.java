package by.panasyuk.dao;

import by.panasyuk.dao.exception.DaoException;
import by.panasyuk.dao.exception.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Abstract JDBC DAO
 *
 * @param <T>  - Identified entity
 * @param <PK> - Type primary key of entity
 */
public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Number> implements GenericDao<T, PK> {
    protected Connection connection;

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;
    protected abstract void prepareStatementForGet(PreparedStatement statement, PK key) throws SQLException;
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    @Override
    public Optional<T> getByPK(PK key) throws DaoException {

        String query = getSelectQuery();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
           prepareStatementForGet(ps,key);
           ResultSet resultSet = ps.executeQuery();
           List<T> list = parseResultSet(resultSet);
           if (list.isEmpty()){
               return Optional.empty();
           }
           return Optional.of(list.get(0));
        } catch (SQLException e) {
            throw new DaoException("prepared statement failed",e);
        }
    }

    @Override
    public List<T> getAll() throws DaoException {

        // Write your code here

        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<T> persist(T object) throws PersistException {

        // Write your code here

        throw new UnsupportedOperationException();
    }

    @Override
    public void update(T object) throws PersistException {

        // Write your code here

        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(T object) throws PersistException {

        // Write your code here

        throw new UnsupportedOperationException();
    }
}