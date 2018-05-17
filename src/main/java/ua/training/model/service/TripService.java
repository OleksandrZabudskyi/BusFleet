package ua.training.model.service;

import ua.training.model.entity.Employee;
import ua.training.model.entity.Trip;

import java.util.List;

public interface TripService {
    List<Trip> getTripsAndRoutes(int offset, int limit);

    int getNumberOfRecords();

    void setBusOnTrip(int tripId, int busId);

    void deleteBusFromTrip(int tripId);

    void setDriverOnTrip(int tripId, int driverId);

    void deleteDriverFromTrip(int tripId);

    List<Trip> getAppointmentTripsToDrivers(Employee employee);

    void setTripConfirmation(int tripId);
}
