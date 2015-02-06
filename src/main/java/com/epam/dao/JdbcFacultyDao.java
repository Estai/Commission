package com.epam.dao;

import com.epam.entity.Application;
import com.epam.entity.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcFacultyDao implements FacultyDao {
    private Connection connection = null;

    public JdbcFacultyDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Faculty> findAll() {
        List<Faculty> faculties = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM FACULTY;");
            while (resultSet.next()) {
                Faculty faculty = new Faculty();
                faculty.setId(resultSet.getInt(1));
                faculty.setName(resultSet.getString(2));
                faculty.setDecan(resultSet.getString(4));
                faculties.add(faculty);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, statement);
        }
        return faculties;
    }


    @Override
    public Faculty findById(Integer id) {
        Faculty faculty = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT NAME,DECAN FROM FACULTY WHERE ID=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                faculty = new Faculty();
                faculty.setId(id);
                faculty.setName(resultSet.getString(1));
                faculty.setDecan(resultSet.getString(2));
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, preparedStatement);
        }
        return faculty;

    }

    @Override
    public boolean delete(Faculty entity) {
        return deleteById(entity.getId());
    }

    @Override
    public Faculty create(Faculty entity) {
        Faculty faculty = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO  FACULTY(NAME,DECAN) VALUES(?,?)");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDecan());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            }
            faculty = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(generatedKeys,preparedStatement);
        }
        return faculty;
    }

    @Override
    public Faculty update(Faculty entity) {
        Faculty faculty = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE FACULTY SET NAME=?,DECAN=? WHERE ID=?");
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDecan());
            preparedStatement.executeUpdate();
            faculty = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(preparedStatement);
        }
        return faculty;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean isDelete = false;
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = connection.prepareStatement("DELETE FROM FACULTY WHERE ID=?");
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
            isDelete = true;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(prepareStatement);
        }

        return isDelete;
    }

    @Override
    public boolean findByName(String name) {
        boolean flag=false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT 1 FROM FACULTY WHERE NAME=?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            flag=resultSet.next();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, preparedStatement);
        }
return flag;
    }
}
