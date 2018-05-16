package ua.training.model.dao.mapper;

import ua.training.constant.Attributes;
import ua.training.model.entity.Bus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusMapper implements EntityMapper<Bus> {
    @Override
    public Bus extractFromResultSet(ResultSet resultSet) throws SQLException {
        Bus bus = new Bus();
        bus.setId(resultSet.getInt(Attributes.BUS_ID));
        bus.setModel(resultSet.getString(Attributes.BUS_MODEL));
        bus.setLicensePlate(resultSet.getString(Attributes.LICENCE_PLATE));
        bus.setManufactureYear(resultSet.getInt(Attributes.MANUFACTURE_YEAR));
        bus.setParkingSpot(resultSet.getString(Attributes.PARKING_SPOT));
        bus.setUsed(resultSet.getBoolean(Attributes.USED));
        return bus;
    }
}
