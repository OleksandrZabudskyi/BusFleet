package ua.training.model.service;

import ua.training.model.entity.Bus;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;
import ua.training.model.entity.Trip;

import java.util.List;

public interface TripService {
    List<Trip> getTripsAndRoutes(int offset, int limit);

    int getNumberOfRecords();

    List<Bus> getAllBuses();

    void setBus(int tripId, int busId);

    void deleteBus(int tripId);

    List<Driver> getAllDrivers();

    void setDriver(int tripId, int driverId);

    void deleteDriver(int tripId);

    List<Trip> getAppointmentTripsToDriver(Employee employee);
}
