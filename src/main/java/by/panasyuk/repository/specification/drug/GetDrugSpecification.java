package by.panasyuk.repository.specification.drug;

import by.panasyuk.domain.Drug;
import by.panasyuk.domain.Manufacturer;
import by.panasyuk.domain.ReleaseForm;
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
            int manufacturerId = resultSet.getInt(5);
            String manufacturerName = resultSet.getString(6);
            int releaseFormId = resultSet.getInt(7);
            String releaseFormDescription = resultSet.getString(8);
            resultDrug.setManufacturer(new Manufacturer(manufacturerId,manufacturerName));
            resultDrug.setReleaseForm(new ReleaseForm(releaseFormId,releaseFormDescription));
            drugList.add(resultDrug);
        }
        return drugList;
    }
}
