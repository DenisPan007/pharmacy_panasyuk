package by.panasyuk.repository.specification.order;

import by.panasyuk.domain.Order;

import java.sql.*;
import java.util.List;

public class GetOrdersByUserId implements GetOrderSpecification {
    @Override
    public List<Order> get(Order order, Connection connection) throws SQLException {
        String query = "select * from drug_order where user_id = ?";
        int userId = order.getUserId();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,userId);
            ResultSet resultSet =  statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}