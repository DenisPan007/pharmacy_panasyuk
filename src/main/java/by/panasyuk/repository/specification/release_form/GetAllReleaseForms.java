package by.panasyuk.repository.specification.release_form;

import by.panasyuk.domain.ReleaseForm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GetAllReleaseForms implements GetReleaseFormSpecification {
        @Override
        public List<ReleaseForm> get(ReleaseForm object, Connection connection) throws SQLException {
            String query = "SELECT * FROM release_form ";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                return parseResultSet(resultSet);
            }
        }
}
