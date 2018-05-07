package ua.training.model.dao.impl;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.EmployeeDao;

import java.sql.Connection;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public EmployeeDao createUserDao(Connection connection) {
        return new EmployeeDaoImpl(connection);
    }
}
