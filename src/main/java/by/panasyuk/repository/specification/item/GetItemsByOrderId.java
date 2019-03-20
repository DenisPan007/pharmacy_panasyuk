package by.panasyuk.repository.specification.item;

import by.panasyuk.domain.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetItemsByOrderId implements GetItemSpecification {
    @Override
    public List<Item> get(Item item, Connection connection) throws SQLException {
        String query = "select dod.id, dod.drug_amount,drug.id,drug.name,drug.price,drug.is_prescription_required,m.id,m.name,rf.id,rf.description,dod.drug_order_id from drug_order_details dod join drug on dod.drug_id = drug.id\n" +
                "join manufacturer m on drug.manufacturer_id = m.id join release_form rf on drug.release_form_id = rf.id where drug_order_id = ?";
        int orderId = item.getOrderId();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,orderId);
            ResultSet resultSet =  statement.executeQuery();
            return parseResultSet(resultSet);
        }
    }
}
