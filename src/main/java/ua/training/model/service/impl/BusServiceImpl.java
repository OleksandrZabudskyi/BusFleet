package ua.training.model.service.impl;

import org.apache.log4j.Logger;
import ua.training.constant.LogMessage;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.ConnectionPoolHolder;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Route;
import ua.training.model.service.BusService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusServiceImpl implements BusService {
    private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Override
    public Map<Bus, Route> getAllBusesWithRoutes() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (BusDao busDao = DaoFactory.getInstance().createBusDao(connection)) {
            return busDao.findAllBusesWithRoutes();
        } catch (Exception e) {
            logger.error(LogMessage.NO_RESULT_FROM_DB, e);
            return new HashMap<Bus, Route>();
        }
    }

    @Override
    public List<Bus> getAllBusesToAllDrivers() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (BusDao busDao = DaoFactory.getInstance().createBusDao(connection)) {
            return busDao.findAllBusesWithDrivers();
        } catch (Exception e) {
            logger.error(LogMessage.NO_RESULT_FROM_DB, e);
            return new ArrayList<>();
        }
    }
}