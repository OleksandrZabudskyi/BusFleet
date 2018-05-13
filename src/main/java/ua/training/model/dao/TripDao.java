package ua.training.model.dao;

import ua.training.model.entity.Trip;

import java.util.List;
import java.util.Optional;

public interface TripDao extends GenericDao<Trip, Integer> {
    Optional<List<Trip>> findTripsWithRoutes(int offset, int limit);
    int getNumberOfRecords();
}
