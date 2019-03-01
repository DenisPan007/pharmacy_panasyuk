package by.panasyuk.repository.specification.user;

import by.panasyuk.repository.specification.Specification;
import by.panasyuk.domain.User;

import java.sql.*;

public class GetAllUsers implements Specification<User> {
    @Override
    public ResultSet get(User user, Connection connection) throws SQLException {
        String query = "SELECT * FROM user ";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}
