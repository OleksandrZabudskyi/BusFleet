package ua.training.model.dao.impl;

import ua.training.constant.Attributes;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.util.SQLQueries;
import ua.training.model.entity.Bus;
import ua.training.model.entity.Route;
import ua.training.model.entity.Trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BusDaoImpl implements BusDao {
    private Connection connection;

    public BusDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Bus> findById(Integer id) {
        Optional<Bus> bus = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.FIND_BUS_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                bus = Optional.ofNullable(geBusFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bus;
    }

    private Bus geBusFromResultSet(ResultSet resultSet) throws SQLException {
        Bus bus = new Bus();
        bus.setBusModel(resultSet.getString(Attributes.BUS_MODEL));
        bus.setLicensePlate(resultSet.getString(Attributes.LICENCE_PLATE));
        bus.setManufactureYear(resultSet.getInt(Attributes.MANUFACTURE_YEAR));
        bus.setParkingSpot(resultSet.getString(Attributes.PARKING_SPOT));
        return bus;
    }

    @Override
    public Optional<List<Bus>> findAll() {
        List<Bus> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_BUSES);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(geBusFromResultSet(resultSet));
            }
            return Optional.of(resultList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Bus entity) throws EntityAlreadyExistException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_BUS)) {
            setBusParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EntityAlreadyExistException(String.valueOf(entity.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setBusParameters(Bus entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getBusModel());
        statement.setString(2, entity.getLicensePlate());
        statement.setInt(3, entity.getManufactureYear());
        statement.setString(4, entity.getParkingSpot());
    }

    @Override
    public void update(Bus entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_BUS_BY_ID)) {
            setBusParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.DELETE_BUS_BY_ID)) {
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

    @Override
    public Optional<List<Bus>> findFreeBuses() {
        List<Bus> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_FREE_BUSES);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(geBusFromResultSet(resultSet));
            }
            return Optional.of(resultList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
