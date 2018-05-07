package ua.training.model.service;

import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.entity.Driver;
import ua.training.model.entity.Employee;

import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findEmployeeByEmail(String email);

    void registerDriver(Driver driver) throws Exception;

    Driver getDriver(String email);

}
