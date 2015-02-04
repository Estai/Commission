package com.epam.dao;

import com.epam.entity.Application;
import com.epam.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcSubjectDao implements SubjectDao {
    private Connection connection = null;

    public JdbcSubjectDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> subjects = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SUBJECT;");
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt(1));
                subject.setName(resultSet.getString(2));
                subject.setMain(resultSet.getInt(3)==1);
                subjects.add(subject);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, statement);
        }
        return subjects;
    }

    @Override
    public Subject findById(Integer id) {
        Subject subject = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM SUBJECT WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subject = new Subject();
                subject.setId(resultSet.getInt(1));
                subject.setName(resultSet.getString(2));
                subject.setMain(resultSet.getInt(3)==1);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, preparedStatement);
        }
        return subject;
    }

    public Subject findByName(String name) {
        Subject subject = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM SUBJECT WHERE NAME=?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subject = new Subject();
                subject.setId(resultSet.getInt(1));
                subject.setName(resultSet.getString(2));
                subject.setMain(resultSet.getInt(3)==1);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, preparedStatement);
        }
        return subject;
    }

    @Override
    public boolean delete(Subject entity) {
        boolean isDelete = false;
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = connection.prepareStatement("DELETE FROM SUBJECT WHERE id=?");
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
    public Subject create(Subject entity) {
        Subject subject = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO  SUBJECT(NAME,MAIN) VALUES(?,?)");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBoolean(2, entity.isMain());
            preparedStatement.executeUpdate();
            subject = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(preparedStatement);
        }
        return subject;
    }

    @Override
    public Subject update(Subject entity) {
        Subject subject = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE SUBJECT SET NAME=?,MAIN=? WHERE id=?");
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.setBoolean(2,entity.isMain());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
            subject = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(preparedStatement);
        }
        return subject;
    }

    @Override
    public List<Subject> findMainSubject() {
        List<Subject> subjects = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SUBJECT WHERE MAIN='1';");
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt(1));
                subject.setName(resultSet.getString(2));
                subject.setMain(resultSet.getInt(3)==1);
                subjects.add(subject);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, statement);
        }
        return subjects;
    }
}
