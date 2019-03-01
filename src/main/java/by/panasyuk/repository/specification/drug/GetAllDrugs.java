package by.panasyuk.repository.specification.drug;

import by.panasyuk.repository.specification.Specification;
import by.panasyuk.domain.Drug;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetAllDrugs implements Specification<Drug> {
    @Override
    public ResultSet get(Drug drug, Connection connection) throws SQLException {
        String query = "SELECT * FROM drug ";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}
