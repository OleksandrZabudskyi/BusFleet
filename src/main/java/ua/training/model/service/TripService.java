package ua.training.model.service;

import ua.training.model.entity.Bus;
import ua.training.model.entity.Trip;

import java.util.List;
import java.util.Optional;

public interface TripService {
    Optional<List<Trip>> getTripsAndRoutes(int offset, int limit);

    int getNumberOfRecords();

    Optional<List<Bus>> getAllBuses();

    void setBus(int tripId, int busId);

    void deleteBus(int tripId);
}
