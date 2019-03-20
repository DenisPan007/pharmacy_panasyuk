package by.panasyuk.repository.impl;

import by.panasyuk.domain.Prescription;
import by.panasyuk.repository.AbstractJdbcRepository;
import by.panasyuk.repository.AutoConnection;
import by.panasyuk.repository.Repository;
import by.panasyuk.repository.exception.RepositoryException;
import by.panasyuk.repository.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PrescriptionRepository extends AbstractJdbcRepository<Prescription,Integer> implements Repository<Prescription,Integer> {
    @AutoConnection
    @Override
    public Prescription add(Prescription prescription) throws RepositoryException {
        String query = "INSERT INTO prescription (description,issue_date,validity_date,drug_id,doctor_id,user_id) " +
                "VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, prescription.getDescription());
            ps.setDate(2, prescription.getIssueDate());
            ps.setDate(3, prescription.getValidityDate());
            ps.setInt(4, prescription.getDrugId());
            ps.setInt(5, prescription.getDoctorId());
            ps.setInt(6, prescription.getUserId());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = (keys.getInt(1));
            prescription.setId(id);
            return prescription;
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
    @AutoConnection
    @Override
    public void update(Prescription prescription) throws RepositoryException {
        String query = "UPDATE  prescription " +
                "SET description=?,issue_date=?,validity_date=?,drug_id=?,doctor_id=?,user_id=? " +
                "where id =? ";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, prescription.getDescription());
            ps.setDate(2, prescription.getIssueDate());
            ps.setDate(3, prescription.getValidityDate());
            ps.setInt(4, prescription.getDrugId());
            ps.setInt(5, prescription.getDoctorId());
            ps.setInt(6, prescription.getUserId());
            ps.setInt(7, prescription.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
    @AutoConnection
    @Override
    public void delete(Prescription object) throws RepositoryException {

    }
    @AutoConnection
    @Override
    public List<Prescription> getQuery(Prescription obj, Specification<Prescription> spec) throws RepositoryException {
        try {
            return spec.get(obj, connection);

        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }
}
