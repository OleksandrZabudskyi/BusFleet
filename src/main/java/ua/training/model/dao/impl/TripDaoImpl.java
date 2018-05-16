package ua.training.model.dao.impl;

import ua.training.constant.Attributes;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.TripDao;
import ua.training.model.dao.mapper.BusMapper;
import ua.training.model.dao.mapper.DriverMapper;
import ua.training.model.dao.mapper.RouteMapper;
import ua.training.model.dao.util.SQLQueries;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Route;
import ua.training.model.entity.Trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripDaoImpl implements TripDao {
    private Connection connection;

    public TripDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Trip> findById(Integer id) {
        Optional<Trip> trip = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.FIND_TRIP_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                trip = Optional.ofNullable(geTripFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trip;
    }

    private Trip geTripFromResultSet(ResultSet resultSet) throws SQLException {
        Trip trip = new Trip();
        trip.setId(resultSet.getInt(Attributes.TRIP_ID));
        trip.setNumber(resultSet.getString(Attributes.TRIP_NUMBER));
        trip.setStartTime(resultSet.getTime(Attributes.TRIP_START_TIME).toLocalTime());
        trip.setEndTime(resultSet.getTime(Attributes.TRIP_END_TIME).toLocalTime());
        Route route = new Route();
        route.setId(resultSet.getInt(Attributes.ROUTE_ID));
        trip.setRoute(route);
        Bus bus = new Bus();
        bus.setId(resultSet.getInt(Attributes.BUS_ID));
        trip.setBus(bus);
        Driver driver = new Driver();
        driver.setId(resultSet.getInt(Attributes.DRIVER_ID));
        trip.setDriver(driver);
        trip.setConfirmation(resultSet.getBoolean(Attributes.CONFIRMATION));
        return trip;
    }

    @Override
    public List<Trip> findAll() {
        List<Trip> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_TRIPS);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(geTripFromResultSet(resultSet));
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Trip entity) throws EntityAlreadyExistException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_TRIP)) {
            setTripParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EntityAlreadyExistException(entity.getNumber());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Trip entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_TRIP_BY_ID)) {
            setTripParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTripParameters(Trip entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getNumber());
        statement.setTime(2, Time.valueOf(entity.getStartTime()));
        statement.setTime(3, Time.valueOf(entity.getEndTime()));
        statement.setInt(4, entity.getRoute().getId());
        int busId = entity.getBus().getId();
        if (busId == 0) {
            statement.setNull(5, java.sql.Types.NULL);
        } else {
            statement.setInt(5, busId);
        }
        int driverId = entity.getDriver().getId();
        if (driverId == 0) {
            statement.setNull(6, java.sql.Types.NULL);
        } else {
            statement.setInt(6, driverId);
        }
        statement.setBoolean(7, entity.isConfirmation());
        statement.setInt(8, entity.getId());
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.DELETE_TRIP_BY_ID)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Trip> findTripsWithRoutes(int offset, int limit) {
        List<Trip> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_TRIPS_WITH_ROUTES)) {
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Trip trip = geTripFromResultSet(resultSet);
                trip.setRoute(new RouteMapper().extractFromResultSet(resultSet));
                resultList.add(trip);
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getNumberOfRecords() {
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.FIND_ALL_TRIPS_COUNT)) {
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(Attributes.ROWS_NUMBER);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Trip> findTripsWithDetailsByDriverId(int driverId) {
        List<Trip> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_TRIPS_WITH_ROUTE_BUSE_DRIVER)) {
            ps.setInt(1, driverId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Trip trip = geTripFromResultSet(resultSet);
                trip.setRoute(new RouteMapper().extractFromResultSet(resultSet));
                trip.setBus(new BusMapper().extractFromResultSet(resultSet));
                trip.setDriver(new DriverMapper().extractFromResultSet(resultSet));
                resultList.add(trip);
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
