package com.epam.dao;

import com.epam.entity.Subject;

import java.util.List;

public interface SubjectDao extends Dao<Subject> {
    public List<Subject> findMainSubject();
}
