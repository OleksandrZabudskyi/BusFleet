package ua.training.model.dao;

import ua.training.model.entity.Bus;
import ua.training.model.entity.Route;

import java.util.List;
import java.util.Map;

public interface BusDao extends GenericDao<Bus, Integer> {
    List<Bus> findAllBusesWithDrivers();

    public Map<Bus, Route> findAllBusesWithRoutes();
}
