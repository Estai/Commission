package com.epam.dao;

import com.epam.entity.StatementEntity;

import java.util.List;

public interface StatementDao extends Dao<StatementEntity> {
    public StatementEntity findByIdAndGroup(int i, String group);

    public List<StatementEntity> findByIdEnrollee(Integer id);
}
