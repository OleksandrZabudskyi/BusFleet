package ua.training.model.service;

import ua.training.model.entity.Bus;

import java.util.List;

public interface BusService {
    List<Bus> getAllBuses();

    List<Bus> getAllBusesToAllDrivers();
}
