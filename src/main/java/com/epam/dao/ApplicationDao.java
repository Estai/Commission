package com.epam.dao;


import com.epam.entity.Application;

import java.util.List;

public interface ApplicationDao extends Dao<Application> {
    public Application findByIdAndGroup(int i, String group);

    public List<Application> findByIdEnrollee(Integer id);
}
