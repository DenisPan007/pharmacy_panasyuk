package by.panasyuk.dao.specification.user;

import by.panasyuk.dao.specification.Specification;
import by.panasyuk.domain.User;

import java.sql.*;

public class GetAll implements Specification<User> {
    @Override
    public ResultSet get(User user, Connection connection) throws SQLException {
        String query = "SELECT * FROM user ";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}
