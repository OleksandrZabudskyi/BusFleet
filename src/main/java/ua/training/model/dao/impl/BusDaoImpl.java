package ua.training.model.dao.impl;

import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.BusDao;
import ua.training.model.dao.mapper.BusMapper;
import ua.training.model.dao.mapper.DriverMapper;
import ua.training.model.dao.util.SQLQueries;
import ua.training.model.entity.Bus;

import java.sql.*;
import java.util.*;

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
                bus = Optional.ofNullable(new BusMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bus;
    }

    @Override
    public List<Bus> findAll() {
        List<Bus> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_BUSES);
             ResultSet resultSet = ps.executeQuery()) {
            BusMapper busMapper = new BusMapper();
            while (resultSet.next()) {
                resultList.add(busMapper.extractFromResultSet(resultSet));
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Bus entity) throws EntityAlreadyExistException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_BUS)) {
            new BusMapper().setParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EntityAlreadyExistException(String.valueOf(entity.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Bus entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_BUS_BY_ID)) {
            new BusMapper().setParameters(entity, statement);
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
    public List<Bus> findAllBusesWithDrivers() {
        Map<Integer, Bus> buses = new HashMap<>();
        Map<Integer, ua.training.model.entity.Driver> drivers = new HashMap<>();

        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_BUSES_WITH_DRIVERS);
             ResultSet resultSet = ps.executeQuery()) {
            BusMapper busMapper = new BusMapper();
            DriverMapper driverMapper = new DriverMapper();
            while (resultSet.next()) {
                Bus bus = busMapper.extractFromResultSet(resultSet);
                ua.training.model.entity.Driver driver = driverMapper.extractFromResultSet(resultSet);
                bus = busMapper.makeUnique(buses, bus);
                driver = driverMapper.makeUnique(drivers, driver);
                bus.getDrivers().add(driver);
            }
            return new ArrayList<>(buses.values());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
