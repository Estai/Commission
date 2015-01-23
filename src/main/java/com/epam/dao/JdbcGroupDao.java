package com.epam.dao;

import com.epam.entity.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcGroupDao implements GroupDao {
    private Connection connection;
    private DaoHelper daoHelper;

    public JdbcGroupDao(Connection connection) {
        daoHelper = new DaoHelper();
        this.connection = connection;
    }

    @Override
    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM GROUPS;");
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt(1));
                group.setName(resultSet.getString(2));
                group.setNumberStudent(resultSet.getInt(3));
                groups.add(group);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, statement);
        }
        return groups;
    }

    @Override
    public Group findById(Integer id) {
        Group group = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT ID,NAME,NUMBERSTUDENT FROM GROUPS WHERE ID=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                group = new Group();
                group.setId(resultSet.getInt(1));
                group.setName(resultSet.getString(2));
                group.setNumberStudent(resultSet.getInt(3));
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, preparedStatement);
        }
        return group;


    }

    @Override
    public boolean delete(Group entity) {
        boolean isDelete = false;
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = connection.prepareStatement("DELETE FROM GROUPS WHERE ID=?");
            prepareStatement.setInt(1, entity.getId());
            prepareStatement.executeUpdate();
            isDelete = true;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(prepareStatement);
        }


        return isDelete;
    }

    @Override
    public Group create(Group entity) {
        Group group = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO  GROUPS(NAME,NUMBERSTUDENT) VALUES(?,?)");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getNumberStudent());
            preparedStatement.executeUpdate();
            group = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(preparedStatement);
        }
        return group;
    }

    @Override
    public Group update(Group entity) {
        Group group = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE GROUPS SET NAME=?,NUMBERSTUDENT=? WHERE ID=?");
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getNumberStudent());

            preparedStatement.executeUpdate();
            group = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(preparedStatement);
        }
        return group;
    }

    @Override
    public List<Group> findByFaculty(Integer id) {
        List<Group> groups = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM GROUPS WHERE ID_FACUL=?;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt(1));
                group.setName(resultSet.getString(2));
                group.setNumberStudent(resultSet.getInt(3));
                groups.add(group);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, preparedStatement);
        }
        return groups;
    }
}
