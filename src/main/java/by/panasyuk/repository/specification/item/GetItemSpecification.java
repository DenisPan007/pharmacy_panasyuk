package by.panasyuk.repository.specification.item;

import by.panasyuk.domain.Drug;
import by.panasyuk.domain.Item;
import by.panasyuk.domain.Manufacturer;
import by.panasyuk.domain.ReleaseForm;
import by.panasyuk.repository.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GetItemSpecification extends Specification<Item> {
    default List<Item> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Item> itemList = new ArrayList<>();
        while (resultSet.next()) {
            Item resultItem = new Item();
            resultItem.setId(resultSet.getInt(1));
            resultItem.setAmount(resultSet.getInt(2));
            Drug drug =new Drug();
            drug.setId(resultSet.getInt(3));
            drug.setName(resultSet.getString(4));
            drug.setPrice(resultSet.getInt(5));
            drug.setPrescriptionRequired(resultSet.getBoolean(6));
            Manufacturer m = new Manufacturer();
            m.setId(resultSet.getInt(7));
            m.setName(resultSet.getString(8));
            ReleaseForm rf = new ReleaseForm();
            rf.setId(resultSet.getInt(9));
            rf.setDescription(resultSet.getString(10));
            drug.setManufacturer(m);
            drug.setReleaseForm(rf);
            resultItem.setDrug(drug);
            resultItem.setOrderId(resultSet.getInt(11));
            itemList.add(resultItem);
        }
        return itemList;
    }
}
