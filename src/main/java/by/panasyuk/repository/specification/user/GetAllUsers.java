package by.panasyuk.repository.specification.user;
import by.panasyuk.domain.User;

import java.sql.*;
import java.util.List;

public class GetAllUsers implements GetUserSpecification {
    @Override
    public List<User> get(User user, Connection connection) throws SQLException {
        String query = "SELECT * FROM user ";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            return parseResultSet(resultSet);
        }
    }
}
