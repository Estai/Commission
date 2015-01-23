package com.epam.dao;

import com.epam.entity.BaseEntity;

import java.sql.SQLException;
import java.util.List;


public interface Dao<T extends BaseEntity> {
    public List<T> findAll();

    public T findById(Integer id);

    public boolean delete(T entity);

    public T create(T entity);

    public T update(T entity);
}
