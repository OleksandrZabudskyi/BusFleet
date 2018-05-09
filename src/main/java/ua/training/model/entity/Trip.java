package ua.training.model.entity;

import java.time.LocalDateTime;

public class Trip {
    private int id;
    private String tripNumber;
    private LocalDateTime tripStartTime;
    private LocalDateTime tripEndTime;
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

    public LocalDateTime getTripStartTime() {
        return tripStartTime;
    }

    public void setTripStartTime(LocalDateTime tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public LocalDateTime getTripEndTime() {
        return tripEndTime;
    }

    public void setTripEndTime(LocalDateTime tripEndTime) {
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

    public static final class TripBuilder {

        private int id;
        private String tripNumber;
        private LocalDateTime tripStartTime;
        private LocalDateTime tripEndTime;
        private Route route;
        private Bus bus;
        private Driver driver;

        public TripBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public TripBuilder setTripNumber(String tripNumber) {
            this.tripNumber = tripNumber;
            return this;
        }

        public TripBuilder setTripStartTime(LocalDateTime tripStartTime) {
            this.tripStartTime = tripStartTime;
            return this;
        }

        public TripBuilder setTripEndTime(LocalDateTime tripEndTime) {
            this.tripEndTime = tripEndTime;
            return this;
        }

        public TripBuilder setRoute(Route route) {
            this.route = route;
            return this;
        }

        public TripBuilder setBus(Bus bus) {
            this.bus = bus;
            return this;
        }

        public TripBuilder setDriver(Driver driver) {
            this.driver = driver;
            return this;
        }

        public Trip createTrip() {
            Trip trip = new Trip();
            trip.setId(id);
            trip.setTripNumber(tripNumber);
            trip.setTripStartTime(tripStartTime);
            trip.setTripEndTime(tripEndTime);
            trip.setRoute(route);
            trip.setBus(bus);
            trip.setDriver(driver);
            return trip;
        }
    }
}
