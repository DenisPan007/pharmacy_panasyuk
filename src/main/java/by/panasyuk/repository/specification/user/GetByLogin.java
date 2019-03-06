package by.panasyuk.repository.specification.user;

import by.panasyuk.repository.specification.Specification;
import by.panasyuk.domain.User;

import java.sql.*;
import java.util.List;

public class GetByLogin implements GetUserSpecification {
    @Override
    public List<User> get(User user, Connection connection) throws SQLException {
        String query = "SELECT * FROM user WHERE login = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getLogin());
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}
