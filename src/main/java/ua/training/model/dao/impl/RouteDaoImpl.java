package ua.training.model.dao.impl;

import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.RouteDao;
import ua.training.model.dao.mapper.RouteMapper;
import ua.training.model.dao.util.SQLQueries;
import ua.training.model.entity.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteDaoImpl implements RouteDao {
    private Connection connection;

    public RouteDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Route> findById(Integer id) {
        Optional<Route> route = Optional.empty();
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.FIND_ROUTE_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                route = Optional.ofNullable(new RouteMapper().extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return route;
    }

    @Override
    public List<Route> findAll() {
        List<Route> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_ROUTES);
             ResultSet resultSet = ps.executeQuery()) {
            RouteMapper routeMapper = new RouteMapper();
            while (resultSet.next()) {
                resultList.add(routeMapper.extractFromResultSet(resultSet));
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Route entity) throws EntityAlreadyExistException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_ROUTE)) {
            new RouteMapper().setParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EntityAlreadyExistException(entity.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Route entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_ROUTE_BY_ID)) {
            new RouteMapper().setParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.DELETE_ROUTE_BY_ID)) {
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