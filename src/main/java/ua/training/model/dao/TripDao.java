package ua.training.model.dao;

import ua.training.model.entity.Trip;

import java.util.List;

public interface TripDao extends GenericDao<Trip, Integer> {
    List<Trip> findTripsWithRoutes(int offset, int limit);

    int getNumberOfRecords();

    List<Trip> findTripsWithDetailsByDriverId(int driverId);
}
