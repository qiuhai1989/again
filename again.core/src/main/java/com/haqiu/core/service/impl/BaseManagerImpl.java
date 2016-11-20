package com.haqiu.core.service.impl;


import com.haqiu.core.repository.BaseRepository;
import com.haqiu.core.service.BaseManager;

import java.io.Serializable;
import java.util.List;
/**
 * BaseService实现类
 * @param <T>实体类型
 * @param <PK>主键类型
 */
public abstract class BaseManagerImpl<T,PK extends Serializable> implements BaseManager<T, PK> {

    protected BaseRepository<T, PK> baseRepository;


    public abstract void setBaseDao(BaseRepository<T, PK> baseRepository);

    @Override
    public T find(PK id) {
        return baseRepository.findOne(id);
    }

    @Override
    public List<T> findAll() {

        return baseRepository.findAll();
    }

    @Override
    public long count() {
        return baseRepository.count();
    }

    @Override
    public boolean exists(PK id) {
        return baseRepository.exists(id);
    }

    @Override
    public void save(T entity) {
        baseRepository.save(entity);
    }

    @Override
    public T update(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public void delete(PK id) {
        baseRepository.delete(id);
    }

    @Override
    public void delete(T entity) {
        baseRepository.delete(entity);
    }

}