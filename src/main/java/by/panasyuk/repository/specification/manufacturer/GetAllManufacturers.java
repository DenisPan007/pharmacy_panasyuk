package by.panasyuk.repository.specification.manufacturer;

import by.panasyuk.domain.Manufacturer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GetAllManufacturers implements GetManufacturerSpecification {
    @Override
    public List<Manufacturer> get(Manufacturer object, Connection connection) throws SQLException {
        String query = "SELECT * FROM manufacturer ";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            return parseResultSet(resultSet);
        }
    }
}
