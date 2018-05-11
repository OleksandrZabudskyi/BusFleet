package ua.training.model.dao.impl;

import ua.training.constant.Attributes;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.TripDao;
import ua.training.model.dao.util.SQLQueries;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Route;
import ua.training.model.entity.Trip;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
        trip.setTripNumber(resultSet.getString(Attributes.TRIP_NUMBER));
        java.sql.Timestamp startTimeStamp = resultSet.getTimestamp(Attributes.TRIP_START_TIME);
        LocalDateTime startDateTime = LocalDateTime
                .ofInstant(startTimeStamp.toInstant(), ZoneOffset.ofHours(0));
        trip.setTripStartTime(startDateTime);
        java.sql.Timestamp endTimeStamp = resultSet.getTimestamp(Attributes.TRIP_END_TIME);
        LocalDateTime endDateTime = LocalDateTime
                .ofInstant(endTimeStamp.toInstant(), ZoneOffset.ofHours(0));
        trip.setTripEndTime(endDateTime);
        Route route = new Route();
        route.setId(resultSet.getInt(Attributes.ROUTE_ID));
        trip.setRoute(route);
        Bus bus = new Bus();
        bus.setId(resultSet.getInt(Attributes.BUS_ID));
        trip.setBus(bus);
        Driver driver = new Driver();
        driver.setId(resultSet.getInt(Attributes.DRIVER_ID));
        trip.setDriver(driver);
        return trip;
    }

    @Override
    public Optional<List<Trip>> findAll() {
        List<Trip> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_TRIPS);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(geTripFromResultSet(resultSet));
            }
            return Optional.of(resultList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Trip entity) throws EntityAlreadyExistException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_TRIP)) {
            setRouteParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EntityAlreadyExistException(entity.getTripNumber());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Trip entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_TRIP_BY_ID)) {
            setRouteParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRouteParameters(Trip entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getTripNumber());
        java.sql.Timestamp startTimeStamp = Timestamp.from(entity.getTripStartTime().toInstant(ZoneOffset.ofHours(0)));
        statement.setTimestamp(2, startTimeStamp);
        java.sql.Timestamp endTimeStamp = Timestamp.from(entity.getTripEndTime().toInstant(ZoneOffset.ofHours(0)));
        statement.setTimestamp(3, endTimeStamp);
        statement.setInt(4, entity.getRoute().getId());
        statement.setInt(5, entity.getBus().getId());
        statement.setInt(6, entity.getDriver().getId());
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
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
