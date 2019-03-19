package by.panasyuk.repository.specification.release_form;

import by.panasyuk.domain.ReleaseForm;

import java.sql.*;
import java.util.List;

public class GetByDescription implements GetReleaseFormSpecification {
    @Override
    public List<ReleaseForm> get(ReleaseForm releaseForm, Connection connection) throws SQLException {
        String query = "SELECT * FROM release_form where description = ? ";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,releaseForm.getDescription());
            ResultSet resultSet =  statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}
