package com.epam.dao;

import com.epam.entity.Enrollee;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface EnrolleeDao extends Dao<Enrollee> {
    public Enrollee findByUser(int id_user);
    public void insertScore(Integer id, Integer id_subject, Integer id_score);
    public Map<Integer,Integer> getScore(Integer id_enrollee);
    public void deleteScore(Integer id);
}
