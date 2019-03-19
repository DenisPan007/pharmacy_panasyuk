package by.panasyuk.repository.specification.order;

import by.panasyuk.domain.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetOrderById implements GetOrderSpecification {
    @Override
    public List<Order> get(Order order, Connection connection) throws SQLException {
        String query = "select * from drug_order where id = ?";
        int id = order.getId();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,id);
            ResultSet resultSet =  statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}
