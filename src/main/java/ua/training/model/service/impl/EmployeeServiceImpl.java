package ua.training.model.service.impl;

import org.apache.log4j.Logger;
import ua.training.constant.LogMessage;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.EmployeeDao;
import ua.training.model.service.EmployeeService;

import java.sql.Connection;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection)) {
            return employeeDao.findByEmail(email);
        } catch (Exception e) {
            logger.error(LogMessage.NO_RESULT_FROM_DB, e);
            return Optional.empty();
        }

    }

    @Override
    public void registerDriver(Driver driver) throws Exception {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection)) {
            driver.setRole(Employee.ROLE.DRIVER);
            employeeDao.create(driver);
        } catch (Exception e) {
            logger.error(LogMessage.DRIVER_REGISTRATION_ERROR, e);
            throw new Exception(e);
        }
    }
}
