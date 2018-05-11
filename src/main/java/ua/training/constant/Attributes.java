package ua.training.constant;

public interface Attributes {
    String DOMAIN = ".*/app/bus-fleet/";
    String EMPTY_SIGN = "";
    String COMMA_SIGN = ",";
    String UTF8 = "UTF-8";
    String MD5 = "MD5";
    String LANGUAGE = "language";
    String ERROR_MESSAGE = "errorMessage";
    String LOGGED_USERS = "loggedUsers";

    String INFO_MESSAGE = "infoMessage";
    String ACTIVE_USER = "activeUser";

    /*Table column name*/
    //user
    String USER_ID = "userId";
    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";
    String EMAIL = "email";
    String PHONE_NUMBER = "phoneNumber";
    String ROLE = "role";
    String PASSWORD = "password";
    String DRIVER_LICENCE_NUMBER = "drivingLicenceNumber";
    String DRIVING_EXPERIENCE = "drivingExperience";
    String PASSPORT_NUMBER = "passportNumber";
    String PASSPORT_REGISTRATION = "passportRegistration";
    String ASSIGNED = "assigned";
    String REGISTERED = "registered";
    //route
    String ROUTE_ID = "routeId";
    String ROTE_NAME = "routeName";
    String DESTINATION_FROM = "destinationFrom";
    String DESTINATION_TO = "destinationTo";
    //bus
    String BUS_MODEL = "busModel";
    String LICENCE_PLATE = "licencePlate";
    String MANUFACTURE_YEAR = "manufactureYear";
    String PARKING_SPOT = "parkingSpot";
    //trip
    String TRIP_NUMBER = "tripNumber";
    String TRIP_START_TIME = "tripStartTime";
    String TRIP_END_TIME = "tripEndTime";
    String BUS_ID = "busId";
    String DRIVER_ID = "driverId";
    String TRIPS = "trips";
}
