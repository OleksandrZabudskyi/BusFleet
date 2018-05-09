package ua.training.model.dao.impl;

import ua.training.constant.Attributes;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.entity.Employee;
import ua.training.model.dao.EmployeeDao;
import ua.training.model.dao.util.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {
    private Connection connection;

    public EmployeeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        Optional<Employee> employee = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.SELECT_BY_EMAIL_QUERY)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                employee = Optional.ofNullable(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        Optional<Employee> employee = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.SELECT_BY_ID_QUERY)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                employee = Optional.ofNullable(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }


    @Override
    public Optional<List<Employee>> findAll() {
        List<Employee> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.SELECT_ALL_QUERY);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(getUserFromResultSet(resultSet));
            }
            return Optional.of(resultList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Employee entity) throws EntityAlreadyExistException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_DRIVER_QUERY)) {
            setUserParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EntityAlreadyExistException(entity.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Employee entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_DRIVER_BY_ID)) {
            setUserParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.DELETE_BY_ID)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void setUserParameters(Employee employee, PreparedStatement statement) throws SQLException {
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setString(3, employee.getEmail());
        statement.setString(4, employee.getPhoneNumber());
        statement.setString(5, employee.getRole().name());
        statement.setString(6, employee.getPassword());

        new EmployeeHandler().setSuccessorParameters(employee, statement);
    }

    private Employee getUserFromResultSet(ResultSet resultSet) throws SQLException {
        Employee.ROLE role = Employee.ROLE.valueOf(resultSet.getString(Attributes.ROLE));

        Employee employee = new EmployeeHandler().extractFromResultSet(role, resultSet);
        employee.setId(resultSet.getInt(Attributes.USER_ID));
        employee.setFirstName(resultSet.getString(Attributes.FIRST_NAME));
        employee.setLastName(resultSet.getString(Attributes.LAST_NAME));
        employee.setEmail(resultSet.getString(Attributes.EMAIL));
        employee.setPassword(resultSet.getString(Attributes.PASSWORD));
        employee.setPhoneNumber(resultSet.getString(Attributes.PHONE_NUMBER));
        employee.setRole(role);
        return employee;
    }


    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
