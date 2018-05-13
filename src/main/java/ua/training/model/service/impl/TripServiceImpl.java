package ua.training.model.service.impl;

import org.apache.log4j.Logger;
import ua.training.constant.LogMessage;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TripDao;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.entity.Trip;
import ua.training.model.service.TripService;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class TripServiceImpl implements TripService{
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
}
