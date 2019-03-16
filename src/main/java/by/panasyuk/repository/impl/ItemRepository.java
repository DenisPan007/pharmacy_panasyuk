package by.panasyuk.repository.impl;

import by.panasyuk.domain.Item;
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


public class ItemRepository extends AbstractJdbcRepository<Item, Integer> implements Repository<Item, Integer> {
    @AutoConnection
    @Override
    public Item add(Item item) throws RepositoryException {
        String query = "INSERT INTO drug_order_details (drug_amount,drug_id,drug_order_id) " +
                "VALUES(?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, item.getAmount());
            ps.setInt(2, item.getDrug().getId());
            ps.setInt(3, item.getOrderId());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = (keys.getInt(1));
            item.setId(id);
            return item;
        } catch (SQLException e) {
            throw new RepositoryException("prepared statement failed", e);
        }
    }

    @AutoConnection
    @Override
    public void update(Item object) throws RepositoryException {

    }

    @AutoConnection
    @Override
    public void delete(Item object) throws RepositoryException {

    }

    @AutoConnection
    @Override
    public List<Item> getQuery(Item obj, Specification<Item> spec) throws RepositoryException {
        return null;
    }
}
