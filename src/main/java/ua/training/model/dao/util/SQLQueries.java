package ua.training.model.dao.util;

public interface SQLQueries {
    String SELECT_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?";
    String INSERT_EMPLOYEE_QUERY = "INSERT INTO user (firstName, lastName, " +
            "email, phoneNumber, role, password, drivingLicenceNumber, expiryDate, drivingExperience) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
}
