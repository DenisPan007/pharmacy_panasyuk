package by.panasyuk.repository.specification.user;

import by.panasyuk.repository.specification.Specification;
import by.panasyuk.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetByEmail implements Specification<User> {
    @Override
    public ResultSet get(User user, Connection connection) throws SQLException {
        String query = "SELECT * FROM user WHERE email = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            return statement.executeQuery();
        }
    }
}
