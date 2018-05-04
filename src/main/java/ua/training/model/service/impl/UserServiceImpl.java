package ua.training.model.service.impl;

import ua.training.model.bean.Driver;
import ua.training.model.bean.User;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.createUserDao();
    }

        @Override
    public Optional<User> findUserByEmail(String email) {
       /* User user = new Driver();
        user.setRole(User.ROLE.ADMIN);
        user.setPassword("1");*/
        return Optional.ofNullable(userDao.findByEmail(email));
    }
}
