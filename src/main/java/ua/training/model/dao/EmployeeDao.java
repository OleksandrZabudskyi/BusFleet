package ua.training.model.dao;

import ua.training.model.entity.Employee;

public interface EmployeeDao extends GenericDao<Employee> {
    Employee findByEmail(String email);

    void deleteByName(String name);
}
