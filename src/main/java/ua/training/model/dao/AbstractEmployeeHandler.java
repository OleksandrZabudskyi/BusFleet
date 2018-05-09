package ua.training.model.dao;

import ua.training.model.entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AbstractEmployeeHandler {
    Employee extractFromResultSet(Employee.ROLE role, ResultSet resultSet) throws SQLException;
    void setSuccessorParameters(Employee employee, PreparedStatement statement) throws SQLException;
}
