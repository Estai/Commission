package com.epam.dao;


import com.epam.entity.Application;
import com.epam.entity.PriorityStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcApplicationDao implements ApplicationDao {
    private Connection connection = null;



    public JdbcApplicationDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Application> findAll() {
        List<Application> applications = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM STATEMENT;");
            while (resultSet.next()) {
                Application application = new Application();
                application.setId(resultSet.getInt(1));
                application.setIdEnrollee(resultSet.getInt(2));
                application.setGroupName(resultSet.getString(3));
                application.setPriority(PriorityStatement.valueOf(resultSet.getString(4).toUpperCase()));
                applications.add(application);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, statement);
        }
        return applications;
    }

    @Override
    public Application findById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Application entity) {
        boolean isDelete = false;
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = connection.prepareStatement("DELETE FROM  STATEMENT WHERE ID=?");
            prepareStatement.setInt(1, entity.getId());
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
    public Application create(Application entity) {
        Application application = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO  STATEMENT( ID_ENR, GROUPNAME, PRIORITY) VALUES(?,?,?)");
            preparedStatement.setInt(1, entity.getIdEnrollee());
            preparedStatement.setString(2, entity.getGroupName());
            preparedStatement.setString(3, entity.getPriority().toString());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            }
            application = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(generatedKeys, preparedStatement);
        }
        return application;
    }

    @Override
    public Application update(Application entity) {
        Application application = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE STATEMENT SET PRIORITY=?  WHERE ID=?");
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.setString(1, entity.getPriority().toString());
            preparedStatement.executeUpdate();
            application = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(preparedStatement);
        }
        return application;
    }

    @Override
    public Application findByIdAndGroup(int id_enrol, String group) {
        Application application = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM STATEMENT WHERE ID_ENR=? AND GROUPNAME=?");
            preparedStatement.setInt(1, id_enrol);
            preparedStatement.setString(2, group);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               application=new Application();
                application.setId(resultSet.getInt(1));
                application.setIdEnrollee(resultSet.getInt(2));
                application.setGroupName(resultSet.getString(3));
                application.setPriority(PriorityStatement.valueOf(resultSet.getString(4).toUpperCase()));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, preparedStatement);
        }
        return application;
    }

    @Override
    public List<Application> findByIdEnrollee(Integer id) {
        List<Application> applications = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM STATEMENT WHERE ID_ENR=? ");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Application application = new Application();
                application.setId(resultSet.getInt(1));
                application.setIdEnrollee(resultSet.getInt(2));
                application.setGroupName(resultSet.getString(3));
                application.setPriority(PriorityStatement.valueOf(resultSet.getString(4).toUpperCase()));
                applications.add(application);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, statement);
        }
        return applications;
    }
}
