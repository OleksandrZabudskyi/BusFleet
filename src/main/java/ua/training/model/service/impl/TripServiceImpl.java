package ua.training.model.service.impl;

import org.apache.log4j.Logger;
import ua.training.constant.LogMessage;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.EmployeeDao;
import ua.training.model.dao.TripDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;
import ua.training.model.entity.Trip;
import ua.training.model.service.TripService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TripServiceImpl implements TripService {
    private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Override
    public List<Trip> getTripsAndRoutes(int offset, int limit) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection)) {
            return tripDao.findTripsWithRoutes(offset, limit);
        } catch (Exception e) {
            logger.error(LogMessage.NO_RESULT_FROM_DB, e);
            return new ArrayList<>();
        }
    }

    @Override
    public int getNumberOfRecords() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection)) {
            return tripDao.getNumberOfRecords();
        } catch (Exception e) {
            logger.error(LogMessage.NO_RESULT_FROM_DB, e);
            return 0;
        }
    }

    @Override
    public List<Bus> getAllBuses() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (BusDao busDao = DaoFactory.getInstance().createBusDao(connection)) {
            return busDao.findAll();
        } catch (Exception e) {
            logger.error(LogMessage.NO_RESULT_FROM_DB, e);
            return new ArrayList<>();
        }
    }

    @Override
    public void setBus(int tripId, int busId) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection);
             BusDao busDao = DaoFactory.getInstance().createBusDao(connection)) {
            connection.setAutoCommit(false);
            Optional<Bus> busOptional = busDao.findById(busId);
            Optional<Trip> tripOptional = tripDao.findById(tripId);

            if (busOptional.isPresent() && tripOptional.isPresent()
                    && isUpdateAllowed(tripOptional.get(), busOptional.get())) {
                Trip trip = tripOptional.get();
                Bus bus = busOptional.get();
                bus.setUsed(true);
                trip.setBus(bus);
                tripDao.update(trip);
                busDao.update(bus);
            }
            connection.commit();
        } catch (Exception e) {
            logger.error(LogMessage.TRANSACTION_ERROR, e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(LogMessage.ROLLBACK_ERROR, e);
            }
        }
    }

    private boolean isUpdateAllowed(Trip trip, Bus bus) {
        return (trip.getBus().getId() == 0) && !bus.isUsed();
    }

    @Override
    public void deleteBus(int tripId) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection);
             BusDao busDao = DaoFactory.getInstance().createBusDao(connection)) {
            Optional<Trip> tripOptional = tripDao.findById(tripId);
            connection.setAutoCommit(false);

            if (tripOptional.isPresent()) {
                Trip trip = tripOptional.get();
                int busId = trip.getBus().getId();
                trip.getBus().setId(0);
                tripDao.update(trip);

                Optional<Bus> busOptional = busDao.findById(busId);
                if (busOptional.isPresent()) {
                    Bus bus = busOptional.get();
                    bus.setUsed(false);
                    busDao.update(bus);
                }
            }
            connection.commit();
        } catch (Exception e) {
            logger.error(LogMessage.TRANSACTION_ERROR, e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(LogMessage.ROLLBACK_ERROR, e);
            }
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection)) {
            List<Employee> employees = employeeDao.findAll();
            return employees.stream()
                    .filter(employee -> employee instanceof Driver)
                    .map(employee -> (Driver) employee).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(LogMessage.NO_RESULT_FROM_DB, e);
            return new ArrayList<>();
        }
    }

    @Override
    public void setDriver(int tripId, int driverId) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection);
             EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection)) {
            connection.setAutoCommit(false);
            Optional<Employee> employeeOptional = employeeDao.findById(driverId);
            Optional<Trip> tripOptional = tripDao.findById(tripId);

            Optional<Driver> driverOptional = employeeOptional.map(employee -> (Driver) employee);

            if (driverOptional.isPresent() && tripOptional.isPresent()
                    && tripOptional.get().getDriver().getId() == 0 && !driverOptional.get().isAssigned()) {
                Trip trip = tripOptional.get();
                Driver driver = driverOptional.get();
                driver.setAssigned(true);
                trip.setDriver(driver);
                tripDao.update(trip);
                employeeDao.update(driver);
            }
            connection.commit();
        } catch (Exception e) {
            logger.error(LogMessage.TRANSACTION_ERROR, e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(LogMessage.ROLLBACK_ERROR, e);
            }
        }
    }

    @Override
    public void deleteDriver(int tripId) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection);
             EmployeeDao employeeDao = DaoFactory.getInstance().createUserDao(connection)) {
            Optional<Trip> tripOptional = tripDao.findById(tripId);
            connection.setAutoCommit(false);

            if (tripOptional.isPresent()) {
                Trip trip = tripOptional.get();
                int driverId = trip.getDriver().getId();
                trip.getDriver().setId(0);
                tripDao.update(trip);

                Optional<Employee> employeeOptional = employeeDao.findById(driverId);
                Optional<Driver> driverOptional = employeeOptional.map(employee -> (Driver) employee);
                if (driverOptional.isPresent()) {
                    Driver driver = driverOptional.get();
                    driver.setAssigned(false);
                    employeeDao.update(driver);
                }
            }
            connection.commit();
        } catch (Exception e) {
            logger.error(LogMessage.TRANSACTION_ERROR, e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(LogMessage.ROLLBACK_ERROR, e);
            }
        }
    }

    @Override
    public List<Trip> getAppointmentTripsToDriver(Employee employee) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection)) {
            return tripDao.findTripsWithDetailsByDriverId(employee.getId());
        } catch (Exception e) {
            logger.error(LogMessage.NO_RESULT_FROM_DB, e);
            return new ArrayList<>();
        }
    }
}
