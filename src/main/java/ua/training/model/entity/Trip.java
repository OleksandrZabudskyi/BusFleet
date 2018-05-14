package ua.training.model.entity;

import java.time.LocalDateTime;

public class Trip {
    private int id;
    private String number;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Route route;
    private Bus bus;
    private Driver driver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
                ", number='" + number + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", route=" + route +
                ", bus=" + bus +
                ", driver=" + driver +
                '}';
    }

    public static final class TripBuilder {

        private int id;
        private String number;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Route route;
        private Bus bus;
        private Driver driver;

        public TripBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public TripBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public TripBuilder setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public TripBuilder setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
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
            trip.setNumber(number);
            trip.setStartTime(startTime);
            trip.setEndTime(endTime);
            trip.setRoute(route);
            trip.setBus(bus);
            trip.setDriver(driver);
            return trip;
        }
    }
}
