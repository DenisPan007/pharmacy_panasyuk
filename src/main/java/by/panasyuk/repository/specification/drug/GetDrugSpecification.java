package by.panasyuk.repository.specification.drug;

import by.panasyuk.domain.Drug;
import by.panasyuk.repository.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GetDrugSpecification extends Specification<Drug> {
    default List<Drug> parseResultSet(ResultSet resultSet) throws SQLException {
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
    }
}
