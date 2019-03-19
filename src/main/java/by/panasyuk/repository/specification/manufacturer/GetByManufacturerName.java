package by.panasyuk.repository.specification.manufacturer;

import by.panasyuk.domain.Manufacturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetByManufacturerName implements GetManufacturerSpecification {
    public List<Manufacturer> get(Manufacturer manufacturer, Connection connection) throws SQLException {
        String name = manufacturer.getName();
        String query = "select * from manufacturer where manufacturer.name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}
