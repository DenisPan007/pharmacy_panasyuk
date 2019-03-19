package by.panasyuk.repository.specification.manufacturer;

import by.panasyuk.domain.Manufacturer;
import by.panasyuk.repository.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GetManufacturerSpecification extends Specification<Manufacturer> {
    default List<Manufacturer> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Manufacturer> manufacturerList = new ArrayList<>();
        while (resultSet.next()) {
            Manufacturer resultManufacturer = new Manufacturer();
            resultManufacturer.setId(resultSet.getInt(1));
            resultManufacturer.setName(resultSet.getString(2));
            manufacturerList.add(resultManufacturer);
        }
        return manufacturerList;
    }
}
