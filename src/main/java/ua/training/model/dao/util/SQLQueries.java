package ua.training.model.dao.util;

public interface SQLQueries {
    String SELECT_ALL_QUERY = "SELECT * FROM USER";
    String SELECT_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?";
    String SELECT_BY_ID_QUERY = "SELECT * FROM user WHERE userId = ?";
    String INSERT_DRIVER_QUERY = "INSERT INTO user (firstName, lastName, " +
            "email, phoneNumber, role, password, drivingLicenceNumber, drivingExperience) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_DRIVER_BY_ID = "UPDATE user SET firstName = ? , lastName = ?, email = ?, phoneNumber = ?" +
            ", role = ?, password = ?, drivingLicenceNumber = ?, drivingExperience = ?  WHERE userId = ?";
    String DELETE_BY_ID = "DELETE FROM user WHERE userId = ?";

}
