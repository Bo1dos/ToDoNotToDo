package com.task.manager.service;

import java.util.List;
import java.util.Optional;

/**
 * T - объект
 * D - его DTO
 * ID - id объекта
 * @param id
 * @return
 */
public interface Service<T, D, ID> {
    public T create(D dto);
    public Optional<T> findById(ID id);
    public List<T> findAll();
    public T update(D dto);
    public void delete(ID id);
}
