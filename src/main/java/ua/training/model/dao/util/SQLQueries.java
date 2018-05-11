package ua.training.model.dao.util;

public interface SQLQueries {
    /*user table*/
    String FIND_ALL_USERS = "SELECT * FROM user";
    String FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    String FIND_USER_BY_ID = "SELECT * FROM user WHERE userId = ?";
    String INSERT_USER = "INSERT INTO user (firstName, lastName, " +
            "email, password, phoneNumber, role, drivingLicenceNumber, drivingExperience, passportNumber," +
            " passportRegistration) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_USER_BY_ID = "UPDATE user SET firstName = ? , lastName = ?, email = ?, password = ?," +
            " phoneNumber = ?, role = ?, drivingLicenceNumber = ?, drivingExperience = ?, passportNumber = ?," +
            " passportRegistration = ? WHERE userId = ?";
    String DELETE_USER_BY_ID = "DELETE FROM user WHERE userId = ?";

    /*route table*/
    String FIND_ALL_ROUTES = "SELECT * FROM route";
    String FIND_ROUTE_BY_ID = "SELECT * FROM route  WHERE routeId = ?";
    String INSERT_ROUTE = "INSERT INTO route (routeName, destinationFrom, destinationTo) VALUES (?, ?, ?)";
    String UPDATE_ROUTE_BY_ID = "UPDATE user SET routeName = ? , destinationFrom = ?, " +
            "destinationTo = ? WHERE routeId = ?";
    String DELETE_ROUTE_BY_ID = "DELETE FROM route WHERE routeId = ?";

    /*trip table*/
    String FIND_ALL_TRIPS = "SELECT * FROM trip";
    String FIND_TRIP_BY_ID = "SELECT * FROM trip  WHERE tripId = ?";
    String INSERT_TRIP = "INSERT INTO trip (tripNumber, tripStartTime, tripEndTime, routeId, busId, driverId)" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    String UPDATE_TRIP_BY_ID = "UPDATE user SET tripNumber = ? , tripStartTime = ?, " +
            "tripEndTime = ? routeId = ? busId = ? driverId = ? WHERE tripId = ?";
    String DELETE_TRIP_BY_ID =  "DELETE FROM trip WHERE tripId = ?";

    /*bus table*/
    String FIND_ALL_BUSES = "SELECT * FROM bus";
    String FIND_BUS_BY_ID = "SELECT * FROM bus  WHERE busId = ?";
    String INSERT_BUS = "INSERT INTO bus (busModel, licensePlate, manufactureYear, parkingSpot) VALUES (?, ?, ?, ?)";
    String UPDATE_BUS_BY_ID = "UPDATE bus SET busModel = ? , licensePlate = ?, manufactureYear = ?, " +
            "parkingSpot = ? WHERE busId = ?";
    String DELETE_BUS_BY_ID = "DELETE FROM bus WHERE busId = ?";
    String FIND_FREE_BUSES = "SELECT *FROM bus WHERE used = 0";

    /*join requests*/
    String FIND_TRIPS_WITH_ROUTES = "SELECT * FROM trip JOIN route ON trip.routeId = route.routeId";

}
