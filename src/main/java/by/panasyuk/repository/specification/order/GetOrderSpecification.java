package by.panasyuk.repository.specification.order;

import by.panasyuk.domain.Item;
import by.panasyuk.domain.Order;
import by.panasyuk.repository.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GetOrderSpecification extends Specification<Order> {
    default List<Order> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()) {
            Order resultOrder = new Order();
            resultOrder.setId(resultSet.getInt(1));
            resultOrder.setUserId(resultSet.getInt(2));
            resultOrder.setStatus(resultSet.getString(3));
            resultOrder.setPrice(resultSet.getInt(4));
            orderList.add(resultOrder);
        }
        return orderList;
    }
}
