package by.panasyuk.repository.specification.drug;

import by.panasyuk.repository.specification.Specification;
import by.panasyuk.domain.Drug;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GetAllDrugs implements GetDrugSpecification {
    @Override
    public List<Drug> get(Drug drug, Connection connection) throws SQLException {
        String query = "SELECT * FROM drug ";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet =  statement.executeQuery(query);
            return parseResultSet(resultSet);
        }
    }
}
