package by.panasyuk.dao.specification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Specification<T> {
    ResultSet get(T object, Connection connection) throws SQLException;
}
