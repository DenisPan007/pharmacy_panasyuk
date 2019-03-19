package by.panasyuk.repository.specification.user;

import by.panasyuk.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetByRole implements GetUserSpecification {
    @Override
    public List<User> get(User user, Connection connection) throws SQLException {
        String query = "SELECT * FROM user WHERE role = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getRole());
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}
