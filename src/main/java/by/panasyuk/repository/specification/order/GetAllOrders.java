package by.panasyuk.repository.specification.order;

import by.panasyuk.domain.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GetAllOrders implements GetOrderSpecification {
    @Override
    public List<Order> get(Order object, Connection connection) throws SQLException {
        String query = "select * from drug_order ";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet =  statement.executeQuery(query);
            return parseResultSet(resultSet);
        }
    }
}
