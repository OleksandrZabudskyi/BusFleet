package ua.training.model.dao.impl;

import ua.training.constant.Attributes;
import ua.training.constant.Messages;
import ua.training.model.dao.AbstractEmployeeHandler;
import ua.training.model.entity.Admin;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeHandler implements AbstractEmployeeHandler {

    @Override
    public Employee extractFromResultSet(Employee.ROLE role, ResultSet resultSet) throws SQLException {
        switch (role) {
            case ADMIN:
                Admin admin = new Admin();
                admin.setPassportNumber(resultSet.getString(Attributes.PASSPORT_NUMBER));
                admin.setPassportRegistration(resultSet.getString(Attributes.PASSPORT_REGISTRATION));
                return admin;
            case DRIVER:
                Driver driver = new Driver();
                driver.setDrivingLicenceNumber(resultSet.getString(Attributes.DRIVER_LICENCE_NUMBER));
                driver.setDrivingExperience(resultSet.getInt(Attributes.DRIVING_EXPERIENCE));
                driver.setAssigned(resultSet.getBoolean(Attributes.ASSIGNED));
                driver.setRegistered(resultSet.getBoolean(Attributes.REGISTERED));
                return driver;
            default:
                throw new IllegalArgumentException(Messages.INVALID_ROLE + role);
        }
    }

    @Override
    public void setSuccessorParameters(Employee employee, PreparedStatement statement) throws SQLException {
        switch (employee.getRole()) {
            case ADMIN:
                Admin admin = (Admin) employee;
                statement.setString(7, admin.getPassportNumber());
                statement.setString(8, admin.getPassportRegistration());
                statement.setNull(9, java.sql.Types.NULL);
                statement.setNull(10, java.sql.Types.NULL);
                return;
            case DRIVER:
                Driver driver = (Driver) employee;
                statement.setString(7, driver.getDrivingLicenceNumber());
                statement.setInt(8, driver.getDrivingExperience());
                statement.setNull(9, java.sql.Types.NULL);
                statement.setNull(10, java.sql.Types.NULL);
                return;
            default:
                throw new IllegalArgumentException(Messages.INVALID_ROLE + employee.getRole());
        }
    }
}
