package ua.training.model.dao.mapper;

import ua.training.constant.Attributes;
import ua.training.model.entity.Admin;
import ua.training.model.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper implements EntityMapper<Admin>{
    @Override
    public Admin extractFromResultSet(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        admin.setId(resultSet.getInt(Attributes.USER_ID));
        admin.setFirstName(resultSet.getString(Attributes.FIRST_NAME));
        admin.setLastName(resultSet.getString(Attributes.LAST_NAME));
        admin.setEmail(resultSet.getString(Attributes.EMAIL));
        admin.setPassword(resultSet.getString(Attributes.PASSWORD));
        admin.setPhoneNumber(resultSet.getString(Attributes.PHONE_NUMBER));
        admin.setRole(Employee.ROLE.ADMIN);
        admin.setPassportNumber(resultSet.getString(Attributes.PASSPORT_NUMBER));
        admin.setPassportRegistration(resultSet.getString(Attributes.PASSPORT_REGISTRATION));
        return admin;
    }
}
