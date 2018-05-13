package ua.training.model.dao.mapper;

import ua.training.constant.Attributes;
import ua.training.model.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper implements EntityMapper<Route> {
    @Override
    public Route extractFromResultSet(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt(Attributes.ROUTE_ID));
        route.setRouteName(resultSet.getString(Attributes.ROTE_NAME));
        route.setDestinationFrom(resultSet.getString(Attributes.DESTINATION_FROM));
        route.setDestinationTo(resultSet.getString(Attributes.DESTINATION_TO));
        return route;
    }
}
