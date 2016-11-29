package com.haqiu.core.service;

import java.io.Serializable;
import java.util.List;

public interface BaseManager<T, PK extends Serializable> {
    public T find(PK id);

    public List<T> findAll();

    public long count();

    public boolean exists(PK id);

    public void save(T entity);

    public T update(T entity);

    public void delete(PK id);

    public void delete(T entity);
}
