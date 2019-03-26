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
        String query = "select drug.id, drug.name, drug.is_prescription_required, drug.price, drug.manufacturer_id, manufacturer.name,  release_form.id, release_form.description, available_amount from drug\n" +
                "join manufacturer on drug.manufacturer_id = manufacturer.id\n" +
                "join release_form on drug.release_form_id = release_form.id";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet =  statement.executeQuery(query);
            return parseResultSet(resultSet);
        }
    }
}
