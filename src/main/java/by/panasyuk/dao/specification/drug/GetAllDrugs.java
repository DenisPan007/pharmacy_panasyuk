package by.panasyuk.dao.specification.drug;

import by.panasyuk.dao.specification.Specification;
import by.panasyuk.domain.Drug;
import by.panasyuk.domain.User;

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
