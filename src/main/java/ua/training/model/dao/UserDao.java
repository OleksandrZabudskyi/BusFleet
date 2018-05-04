package ua.training.model.dao;

import ua.training.model.bean.User;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email);

    void deleteByName(String name);
}
