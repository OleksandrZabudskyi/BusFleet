package ua.training.model.service.impl;

import org.apache.log4j.Logger;
import ua.training.constant.LogMessage;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TripDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Trip;
import ua.training.model.service.TripService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TripServiceImpl implements TripService {
    private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Override
    public Optional<List<Trip>> getTripsAndRoutes(int offset, int limit) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (TripDao tripDao = DaoFactory.getInstance().createTripDao(connection)) {
            return tripDao.findTripsWithRoutes(offset, limit);
        } catch (Exception e) {
            logger.error(LogMessage.NO_RESULT_FROM_DB, e);
            return Optional.empty();
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
    public Optional<List<Bus>> getFreeBuses(int offset, int limit) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (BusDao busDao = DaoFactory.getInstance().createBusDao(connection)) {
            return busDao.findFreeBuses();
        } catch (Exception e) {
            logger.error(LogMessage.NO_RESULT_FROM_DB, e);
            return Optional.empty();
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
            if (busOptional.isPresent() && tripOptional.isPresent()) {
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
}
