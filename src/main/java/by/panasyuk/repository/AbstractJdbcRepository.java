package by.panasyuk.repository;

import java.sql.*;
/**
 * Abstract JDBC DAO
 *
 * @param <T>  - Identified entity
 * @param <PK> - Type primary key of entity
 */
public abstract class AbstractJdbcRepository<T extends Identified<PK>, PK extends Number> implements Repository<T, PK> {
    protected Connection connection;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
