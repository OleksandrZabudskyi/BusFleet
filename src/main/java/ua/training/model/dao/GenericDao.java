package ua.training.model.dao;

import ua.training.exeptions.EntityAlreadyExistException;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, ID> extends AutoCloseable {
    Optional<T> findById(ID id);

    List<T> findAll();

    void create(T entity) throws EntityAlreadyExistException;

    void update(T entity);

    void delete(ID id);
}
