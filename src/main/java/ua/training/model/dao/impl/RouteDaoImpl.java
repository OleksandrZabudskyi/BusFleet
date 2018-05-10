package ua.training.model.dao.impl;

import ua.training.constant.Attributes;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.dao.RouteDao;
import ua.training.model.dao.util.SQLQueries;
import ua.training.model.entity.Employee;
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
                route = Optional.ofNullable(getRouteFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return route;
    }

    private Route getRouteFromResultSet(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt(Attributes.ROUTE_ID));
        route.setRouteName(resultSet.getString(Attributes.ROTE_NAME));
        route.setDestinationFrom(resultSet.getString(Attributes.DESTINATION_FROM));
        route.setDestinationTo(resultSet.getString(Attributes.DESTINATION_TO));
        return route;
    }

    @Override
    public Optional<List<Route>> findAll() {
        List<Route> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQLQueries.FIND_ALL_ROUTES);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(getRouteFromResultSet(resultSet));
            }
            return Optional.of(resultList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Route entity) throws EntityAlreadyExistException {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_ROUTE)) {
            setRouteParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EntityAlreadyExistException(entity.getRouteName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Route entity) {
        try (PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_ROUTE_BY_ID)) {
            setRouteParameters(entity, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRouteParameters(Route route, PreparedStatement statement) throws SQLException {
        statement.setString(1, route.getRouteName());
        statement.setString(2, route.getDestinationFrom());
        statement.setString(3, route.getDestinationTo());
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
