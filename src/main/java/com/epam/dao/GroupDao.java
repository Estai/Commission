package com.epam.dao;

import com.epam.entity.Group;

import java.util.List;

public interface GroupDao extends Dao<Group> {
    public List<Group> findByFaculty(Integer id);
}
