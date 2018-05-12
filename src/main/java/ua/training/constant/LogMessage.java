package ua.training.constant;

public interface LogMessage {
    String REMOVE_USER = "Remove logged user from context: ";
    String ADD_USER = "Add user to context: ";
    String NO_RESULT_FROM_DB = "No result from database";
    String DRIVER_REGISTRATION_ERROR = "Invalid driver registration";
    String SESSION_CREATED = "Session created. Session ID: ";
    String SESSION_DESTROYED = "Session destroyed. Session ID: ";
    String USER_ALREADY_LOGGED = "Already logged user with email:";
}