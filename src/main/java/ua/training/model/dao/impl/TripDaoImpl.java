package ua.training.model.dao.impl;

import ua.training.constant.Attributes;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.TripDao;
import ua.training.model.dao.mapper.BusMapper;
import ua.training.model.dao.mapper.DriverMapper;
import ua.training.model.dao.mapper.RouteMapper;
import ua.training.model.dao.mapper.TripMapper;
import ua.training.model.dao.util.SQLQueries;
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
                trip = Optional.ofNullable(new TripMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trip;
    }

    @Override
    public List<Trip> findAll() {
        List<Trip> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_TRIPS);
             ResultSet resultSet = ps.executeQuery()) {
            TripMapper tripMapper = new TripMapper();
            while (resultSet.next()) {
                resultList.add(tripMapper.extractFromResultSet(resultSet));
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Trip entity) throws EntityAlreadyExistException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_TRIP)) {
            new TripMapper().setParameters(entity, statement);
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
            new TripMapper().setParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

            TripMapper tripMapper = new TripMapper();
            RouteMapper routeMapper = new RouteMapper();
            while (resultSet.next()) {
                Trip trip = tripMapper.extractFromResultSet(resultSet);
                trip.setRoute(routeMapper.extractFromResultSet(resultSet));
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
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_TRIPS_WITH_ROUTE_BUS_DRIVER)) {
            ps.setInt(1, driverId);
            ResultSet resultSet = ps.executeQuery();

            TripMapper tripMapper = new TripMapper();
            RouteMapper routeMapper = new RouteMapper();
            BusMapper busMapper = new BusMapper();
            DriverMapper driverMapper = new DriverMapper();
            while (resultSet.next()) {
                Trip trip = tripMapper.extractFromResultSet(resultSet);
                trip.setRoute(routeMapper.extractFromResultSet(resultSet));
                trip.setBus(busMapper.extractFromResultSet(resultSet));
                trip.setDriver(driverMapper.extractFromResultSet(resultSet));
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
