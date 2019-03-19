package by.panasyuk.repository.specification.release_form;

import by.panasyuk.domain.ReleaseForm;
import by.panasyuk.repository.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface GetReleaseFormSpecification extends Specification<ReleaseForm> {
    default List<ReleaseForm> parseResultSet(ResultSet resultSet) throws SQLException {
        List<ReleaseForm> manufacturerList = new ArrayList<>();
        while (resultSet.next()) {
            ReleaseForm resultReleaseForm = new ReleaseForm();
            resultReleaseForm.setId(resultSet.getInt(1));
            resultReleaseForm.setDescription(resultSet.getString(2));
            manufacturerList.add(resultReleaseForm);
        }
        return manufacturerList;
    }
}
