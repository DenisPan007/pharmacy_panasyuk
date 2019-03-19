package by.panasyuk.repository.specification.prescription;

import by.panasyuk.domain.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetByUserAndDrugId implements GetPrescriptionSpecification {
    @Override
    public List<Prescription> get(Prescription prescription, Connection connection) throws SQLException {
        String query = "SELECT * FROM prescription WHERE user_id = ? and drug_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, prescription.getUserId());
            statement.setInt(2, prescription.getDrugId());
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}
