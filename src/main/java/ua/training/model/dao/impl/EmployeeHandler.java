package ua.training.model.dao.impl;

import ua.training.constant.Attributes;
import ua.training.constant.Messages;
import ua.training.model.dao.AbstractEmployeeHandler;
import ua.training.model.dao.mapper.AdminMapper;
import ua.training.model.dao.mapper.DriverMapper;
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
                return new AdminMapper().extractFromResultSet(resultSet);
            case DRIVER:
                return new DriverMapper().extractFromResultSet(resultSet);
            default:
                throw new IllegalArgumentException(Messages.INVALID_ROLE + role);
        }
    }

    @Override
    public void setSuccessorParameters(Employee employee, PreparedStatement statement) throws SQLException {
        switch (employee.getRole()) {
            case ADMIN:
                Admin admin = (Admin) employee;
                statement.setNull(7, java.sql.Types.NULL);
                statement.setNull(8, java.sql.Types.NULL);
                statement.setString(9, admin.getPassportNumber());
                statement.setString(10, admin.getPassportRegistration());
                statement.setNull(11, java.sql.Types.NULL);
                statement.setNull(12, java.sql.Types.NULL);
                statement.setInt(13, admin.getId());
                return;
            case DRIVER:
                Driver driver = (Driver) employee;
                statement.setString(7, driver.getDrivingLicenceNumber());
                statement.setInt(8, driver.getDrivingExperience());
                statement.setNull(9, java.sql.Types.NULL);
                statement.setNull(10, java.sql.Types.NULL);
                statement.setBoolean(11, driver.isAssigned());
                statement.setBoolean(12, driver.isRegistered());
                statement.setInt(13, driver.getId());
                return;
            default:
                throw new IllegalArgumentException(Messages.INVALID_ROLE + employee.getRole());
        }
    }
}
