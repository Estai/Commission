package com.epam.dao;

import com.epam.entity.Application;
import com.epam.entity.Group;
import com.epam.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcGroupDao implements GroupDao {
    private Connection connection;
    public JdbcGroupDao(Connection connection) {
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
                group.setIdFaculty(resultSet.getInt(4));
                Subject subject=new Subject();
                subject.setId(resultSet.getInt(5));
                group.setProfileSubject(subject);
                groups.add(group);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, statement);
        }
        return groups;
    }

    @Override
    public Group findById(Integer id) {
        Group group = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT ID,NAME,NUMBERSTUDENT,IDPROFILESUBJ FROM GROUPS WHERE ID=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                group = new Group();
                group.setId(resultSet.getInt(1));
                group.setName(resultSet.getString(2));
                group.setNumberStudent(resultSet.getInt(3));
                Subject subject=new Subject();
                subject.setId(resultSet.getInt(4));
                group.setProfileSubject(subject);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, preparedStatement);
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
            DaoHelper.close(prepareStatement);
        }


        return isDelete;
    }

    @Override
    public Group create(Group entity) {
        Group group = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO  GROUPS(NAME,NUMBERSTUDENT,IDPROFILESUBJ) VALUES(?,?,?)");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getNumberStudent());
            preparedStatement.setInt(3, entity.getProfileSubject().getId());
            preparedStatement.executeUpdate();
            group = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(preparedStatement);
        }
        return group;
    }

    @Override
    public Group update(Group entity) {
        Group group = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE GROUPS SET NAME=?,NUMBERSTUDENT=?,IDPROFILESUBJ=? WHERE ID=?");
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getNumberStudent());
            preparedStatement.setInt(3, entity.getProfileSubject().getId());
            preparedStatement.executeUpdate();
            group = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(preparedStatement);
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
                group.setIdFaculty(resultSet.getInt(4));
                Subject subject=new Subject();
                subject.setId(resultSet.getInt(5));
                group.setProfileSubject(subject);
                groups.add(group);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, preparedStatement);
        }
        return groups;
    }
}
