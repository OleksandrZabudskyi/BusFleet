package ua.training.model.dao;

import ua.training.model.entity.Bus;

import java.util.List;
import java.util.Optional;

public interface BusDao extends GenericDao<Bus, Integer> {
    Optional<List<Bus>> findFreeBuses();
}
