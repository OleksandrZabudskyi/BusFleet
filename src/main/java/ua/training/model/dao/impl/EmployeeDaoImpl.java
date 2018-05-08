package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;
import ua.training.model.dao.EmployeeDao;
import ua.training.model.dao.util.SQLQueries;

import java.sql.*;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private static Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
    private Connection connection;

    public EmployeeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = new Employee.EmployeeBuilder().createEmployee();
        employee.setEmail(email);

        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.SELECT_BY_EMAIL_QUERY)) {
            stmt.setString(1, employee.getEmail());
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                employee = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public void create(Employee entity) throws EntityAlreadyExistException {
        Driver driver = (Driver) entity;
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.INSERT_EMPLOYEE_QUERY)) {
            stmt.setString(1, driver.getFirstName());
            stmt.setString(2, driver.getLastName());
            stmt.setString(3, driver.getEmail());
            stmt.setString(4, driver.getPhoneNumber());
            stmt.setString(5, driver.getRole().DRIVER.name());
            stmt.setString(6, driver.getPassword());
            stmt.setString(7, driver.getDrivingLicenceNumber());
            stmt.setInt(8, driver.getExpiryDate());
            stmt.setInt(9, driver.getDrivingExperience());
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EntityAlreadyExistException(driver.getEmail());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Employee entity) {

    }

    @Override
    public void delete(int id) {

    }

    private Employee getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee.EmployeeBuilder().createEmployee();
        employee.setId(resultSet.getInt(Attributes.USER_ID));
        employee.setFirstName(resultSet.getString(Attributes.FIRST_NAME));
        employee.setLastName(resultSet.getString(Attributes.LAST_NAME));
        employee.setEmail(resultSet.getString(Attributes.EMAIL));
        employee.setPassword(resultSet.getString(Attributes.PASSWORD));
        employee.setPhoneNumber(resultSet.getString(Attributes.PHONE_NUMBER));
        employee.setRole(Employee.ROLE.valueOf(resultSet.getString(Attributes.ROLE)));
        return employee;
    }


    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
