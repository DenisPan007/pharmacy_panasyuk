package by.panasyuk.repository.specification.drug;

import by.panasyuk.domain.Drug;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetDrugById implements GetDrugSpecification {
    @Override
    public List<Drug> get(Drug drug, Connection connection) throws SQLException {
        int id = drug.getId();
        String query = "select drug.id, drug.name, drug.is_prescription_required, drug.price, drug.manufacturer_id, manufacturer.name,  release_form.id, release_form.description, available_amount from drug\n" +
                "join manufacturer on drug.manufacturer_id = manufacturer.id\n" +
                "join release_form on drug.release_form_id = release_form.id where drug.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,id);
            ResultSet resultSet =  statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}
