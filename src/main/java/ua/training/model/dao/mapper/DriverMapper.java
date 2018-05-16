package ua.training.model.dao.mapper;

import ua.training.constant.Attributes;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverMapper implements EntityMapper<Driver> {
    @Override
    public Driver extractFromResultSet(ResultSet resultSet) throws SQLException {
        Driver driver = new Driver();
        driver.setId(resultSet.getInt(Attributes.USER_ID));
        driver.setFirstName(resultSet.getString(Attributes.FIRST_NAME));
        driver.setLastName(resultSet.getString(Attributes.LAST_NAME));
        driver.setEmail(resultSet.getString(Attributes.EMAIL));
        driver.setPassword(resultSet.getString(Attributes.PASSWORD));
        driver.setPhoneNumber(resultSet.getString(Attributes.PHONE_NUMBER));
        driver.setRole(Employee.ROLE.DRIVER);
        driver.setDrivingLicenceNumber(resultSet.getString(Attributes.DRIVER_LICENCE_NUMBER));
        driver.setDrivingExperience(resultSet.getInt(Attributes.DRIVING_EXPERIENCE));
        driver.setAssigned(resultSet.getBoolean(Attributes.ASSIGNED));
        driver.setRegistered(resultSet.getBoolean(Attributes.REGISTERED));
        return driver;
    }
}
