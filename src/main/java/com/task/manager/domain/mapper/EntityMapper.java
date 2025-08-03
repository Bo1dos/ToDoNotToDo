package com.task.manager.domain.mapper;

public interface EntityMapper<E, D> {
    public E toEntity(D dto);
    public D toDto(E entity);
}
