package com.epam.dao;


import com.epam.entity.PriorityStatement;
import com.epam.entity.StatementEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStatementDao implements StatementDao {
    private Connection connection = null;
    private DaoHelper daoHelper;

    public JdbcStatementDao(Connection connection) {
        this.connection = connection;
        daoHelper = new DaoHelper();
    }


    @Override
    public List<StatementEntity> findAll() {
        List<StatementEntity> entityList = new ArrayList<StatementEntity>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM STATEMENT;");
            while (resultSet.next()) {
                StatementEntity statementEntity = new StatementEntity();
                statementEntity.setId(resultSet.getInt(1));
                statementEntity.setIdEnrollee(resultSet.getInt(2));
                statementEntity.setGroupName(resultSet.getString(3));
                statementEntity.setPriority(PriorityStatement.valueOf(resultSet.getString(4).toUpperCase()));
                entityList.add(statementEntity);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, statement);
        }
        return entityList;
    }

    @Override
    public StatementEntity findById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(StatementEntity entity) {
        return false;
    }

    @Override
    public StatementEntity create(StatementEntity entity) {
        StatementEntity statement = null;
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
            statement = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(generatedKeys, preparedStatement);
        }
        return statement;
    }

    @Override
    public StatementEntity update(StatementEntity entity) {
        return null;
    }

    @Override
    public StatementEntity findByIdAndGroup(int id_enrol, String group) {
        StatementEntity statementEntity = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM STATEMENT WHERE ID_ENR=? AND GROUPNAME=?");
            preparedStatement.setInt(1, id_enrol);
            preparedStatement.setString(2, group);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                statementEntity = new StatementEntity();
                statementEntity.setId(resultSet.getInt(1));
                statementEntity.setIdEnrollee(resultSet.getInt(2));
                statementEntity.setGroupName(resultSet.getString(3));
                statementEntity.setPriority(PriorityStatement.valueOf(resultSet.getString(4).toUpperCase()));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, preparedStatement);
        }
        return statementEntity;
    }

    @Override
    public List<StatementEntity> findByIdEnrollee(Integer id) {
        List<StatementEntity> entityList = new ArrayList<StatementEntity>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM STATEMENT WHERE ID_ENR=? ");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StatementEntity statementEntity = new StatementEntity();
                statementEntity.setId(resultSet.getInt(1));
                statementEntity.setIdEnrollee(resultSet.getInt(2));
                statementEntity.setGroupName(resultSet.getString(3));
                statementEntity.setPriority(PriorityStatement.valueOf(resultSet.getString(4).toUpperCase()));
                entityList.add(statementEntity);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, statement);
        }
        return entityList;
    }
}
