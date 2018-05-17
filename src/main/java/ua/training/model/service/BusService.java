package ua.training.model.service;

import ua.training.model.entity.Bus;
import ua.training.model.entity.Route;

import java.util.List;
import java.util.Map;

public interface BusService {
    Map<Bus, Route> getAllBusesWithRoutes();

    List<Bus> getAllBusesToAllDrivers();
}
