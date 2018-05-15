package ua.training.model.service;

public interface SecurityService {
    String makePasswordHash(String password);
    boolean comparePasswords(String password, String hashedAndSaltedPassword);
}
