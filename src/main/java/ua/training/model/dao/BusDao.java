package ua.training.model.dao;

import ua.training.model.entity.Bus;

import java.util.List;

public interface BusDao extends GenericDao<Bus, Integer> {
    List<Bus> findFreeBuses();
}
