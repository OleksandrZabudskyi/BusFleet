package ua.training.model.entity;

import java.time.LocalDate;

public class Trip {
    private int id;
    private String tripNumber;
    private LocalDate tripStartTime;
    private LocalDate tripEndTime;
    private Route route;
    private Bus bus;
    private Driver driver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }

    public LocalDate getTripStartTime() {
        return tripStartTime;
    }

    public void setTripStartTime(LocalDate tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public LocalDate getTripEndTime() {
        return tripEndTime;
    }

    public void setTripEndTime(LocalDate tripEndTime) {
        this.tripEndTime = tripEndTime;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", tripNumber='" + tripNumber + '\'' +
                ", tripStartTime=" + tripStartTime +
                ", tripEndTime=" + tripEndTime +
                ", route=" + route +
                ", bus=" + bus +
                ", driver=" + driver +
                '}';
    }
}
