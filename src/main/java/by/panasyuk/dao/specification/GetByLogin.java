package by.panasyuk.dao.specification;

import by.panasyuk.domain.User;

import java.sql.*;

public class GetByLogin implements Specification<User> {
    @Override
    public ResultSet get(User user, Connection connection) throws SQLException {
        String query = "SELECT * FROM user WHERE login = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,user.getLogin());
        return statement.executeQuery();
    }
}
