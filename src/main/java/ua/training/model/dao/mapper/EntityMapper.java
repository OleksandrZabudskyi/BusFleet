package ua.training.model.dao.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<T> {
    T extractFromResultSet(ResultSet resultSet) throws SQLException;

    void setParameters(T entity, PreparedStatement statement) throws SQLException;
}
