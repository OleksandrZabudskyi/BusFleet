package ua.training.model.dao.util;

public interface SQLQueries {
    String SELECT_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?";
}
