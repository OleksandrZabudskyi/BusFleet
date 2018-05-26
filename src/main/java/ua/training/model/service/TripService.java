package ua.training.model.service;

import ua.training.exeptions.EntityAlreadyHandledException;
import ua.training.exeptions.ServiceException;
import ua.training.model.entity.Employee;
import ua.training.model.entity.Trip;

import java.util.List;

/**
 * Provide service layer to manipulate with data from trip dao
 *
 * @author Zabudskyi Oleksandr
 */
public interface TripService {
    List<Trip> getTripsAndRoutes(int offset, int limit);

    int getNumberOfRecords();

    void setBusOnTrip(int tripId, int busId) throws ServiceException;

    void deleteBusFromTrip(int tripId);

    void setDriverOnTrip(int tripId, int driverId) throws ServiceException;

    void deleteDriverFromTrip(int tripId);

    List<Trip> getAppointmentTripsToDrivers(Employee employee);

    void setTripConfirmation(int tripId);
}
