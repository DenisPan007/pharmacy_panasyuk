package by.panasyuk.dao.specification.user;

import by.panasyuk.dao.specification.Specification;
import by.panasyuk.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetById implements Specification<User> {
    @Override
    public ResultSet get(User user, Connection connection) throws SQLException {
        String query = "SELECT * FROM user WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,user.getId());
        return statement.executeQuery();
    }
}
