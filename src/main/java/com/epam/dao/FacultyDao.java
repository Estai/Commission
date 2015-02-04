package com.epam.dao;

import com.epam.entity.Faculty;

public interface FacultyDao extends Dao<Faculty> {
  public boolean deleteById(Integer id);
}
