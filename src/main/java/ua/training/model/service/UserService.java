package ua.training.model.service;

import ua.training.model.bean.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);
}
