package ua.training.model.dao.impl;

import ua.training.model.dao.*;

import java.sql.Connection;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public EmployeeDao createUserDao(Connection connection) {
        return new EmployeeDaoImpl(connection);
    }

    @Override
    public RouteDao createRouteDao(Connection connection) {
        return new RouteDaoImpl(connection);
    }

    @Override
    public TripDao createTripDao(Connection connection) {
        return new TripDaoImpl(connection);
    }

    @Override
    public BusDao createBusDao(Connection connection) {
        return new BusDaoImpl(connection);
    }
}
