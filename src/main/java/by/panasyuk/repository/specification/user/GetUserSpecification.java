package by.panasyuk.repository.specification.user;

import by.panasyuk.domain.User;
import by.panasyuk.repository.specification.Specification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface  GetUserSpecification extends Specification<User> {
    default List<User> parseResultSet(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User resultUser = new User();
            resultUser.setId(resultSet.getInt(1));
            resultUser.setLogin(resultSet.getString(2));
            resultUser.setPassword(resultSet.getString(3));
            resultUser.setFirstName(resultSet.getString(4));
            resultUser.setLastName(resultSet.getString(5));
            resultUser.setEmail(resultSet.getString(6));
            resultUser.setRole(resultSet.getString(7));
            resultUser.setAddress(resultSet.getString(8));
            userList.add(resultUser);
        }
        return userList;
    }
}
