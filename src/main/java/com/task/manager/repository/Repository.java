package com.task.manager.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    void add(T task);
    Optional<T> findById(ID id);
    List<T> findAll();
    void update(T task);
    void delete(ID id);
    Integer count();
    
}
