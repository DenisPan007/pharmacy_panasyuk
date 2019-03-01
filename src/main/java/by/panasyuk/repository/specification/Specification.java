package by.panasyuk.repository.specification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
@FunctionalInterface
public interface Specification<T> {
    ResultSet get(T object, Connection connection) throws SQLException;
}
