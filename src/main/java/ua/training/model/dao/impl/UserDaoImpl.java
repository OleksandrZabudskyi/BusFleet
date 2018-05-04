package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.constant.ColumnName;
import ua.training.model.bean.User;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.util.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();
        user.setEmail(email);

        try (PreparedStatement stmt = connection.prepareStatement(SQLQueries.SELECT_BY_EMAIL_QUERY)) {
            stmt.setString(1, user.getEmail());
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    user = getUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ColumnName.USER_ID));
        user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
        user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
        user.setEmail(resultSet.getString(ColumnName.EMAIL));
        user.setPassword(resultSet.getString(ColumnName.PASSWORD));
        user.setPhoneNumber(resultSet.getString(ColumnName.PHONE_NUMBER));
        user.setRole(User.ROLE.valueOf(resultSet.getString(ColumnName.ROLE)));
        return user;
    }



    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
