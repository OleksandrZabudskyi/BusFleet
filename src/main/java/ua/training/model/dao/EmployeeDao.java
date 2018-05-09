package ua.training.model.dao;

import ua.training.model.entity.Employee;

import java.util.Optional;

public interface EmployeeDao extends GenericDao<Employee, Integer> {
    Optional<Employee> findByEmail(String email);
}
