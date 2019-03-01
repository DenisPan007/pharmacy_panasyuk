package by.panasyuk.repository.impl;

import by.panasyuk.repository.AbstractJdbcRepository;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.Specification;
import by.panasyuk.domain.Drug;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DrugRepository extends AbstractJdbcRepository<Drug, Integer> implements Repository<Drug, Integer> {@Override
public Drug add(Drug drug) throws RepositoryException {
    String query = "INSERT INTO drug (name,isPrescriptionRequired,price) " +
            "VALUES(?,?,?)";
    try {
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, drug.getName());
        ps.setBoolean(2, drug.getIsPrescriptionRequired());
        ps.setInt(3, drug.getPrice());
        ps.executeUpdate();
        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        int id = (keys.getInt(1));
        drug.setId(id);
        return drug;
    } catch (SQLException e) {
        throw new RepositoryException("prepared statement failed", e);
    }
}

    @Override
    public void update(Drug drug) throws RepositoryException {

        String query = "UPDATE drug " +
                "SET name=?, is_prescription_required=?, price=?" +
                "WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, drug.getName());
            ps.setBoolean(2, drug.getIsPrescriptionRequired());
            ps.setInt(3, drug.getPrice());
            ps.setInt(4, drug.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }

    @Override
    public void delete(Drug drug) throws RepositoryException {

        String query = "DELETE FROM drug WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, drug.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);

        }
    }

    @Override
    public List<Drug> getQuery (Drug drug, Specification< Drug > spec) throws RepositoryException {
        try {
            ResultSet resultSet = spec.get(drug, connection);
            List<Drug> drugList = new ArrayList<>();
            while (resultSet.next()) {
                Drug resultDrug = new Drug();
                resultDrug.setId(resultSet.getInt(1));
                resultDrug.setName(resultSet.getString(2));
                resultDrug.setPrescriptionRequired(resultSet.getBoolean(3));
                resultDrug.setPrice(resultSet.getInt(4));
                drugList.add(resultDrug);
            }
            return drugList;
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
}