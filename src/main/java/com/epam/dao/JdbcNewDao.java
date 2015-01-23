package com.epam.dao;

import com.epam.entity.New;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcNewDao implements NewDao {
    private Connection connection;
    private DaoHelper daoHelper;

    public JdbcNewDao(Connection connection) {
        this.connection = connection;
        daoHelper = new DaoHelper();
    }

    @Override
    public List<New> findAll() {
        List<New> news = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM NEW;");
            while (resultSet.next()) {
                New new1 = new New();
                new1.setId(resultSet.getInt(1));
                new1.setH1(resultSet.getString(2));
                new1.setText(resultSet.getString(3));
                new1.setHeader(resultSet.getString(4));
                new1.setPathImage(resultSet.getString(5));
                new1.setDate(resultSet.getDate(6));
                news.add(new1);


            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, statement);
        }
        return news;
    }

    @Override
    public New findById(Integer id) {
        New new1 = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM NEW WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                new1 = new New();
                new1.setId(resultSet.getInt(1));
                new1.setH1(resultSet.getString(2));
                new1.setText(resultSet.getString(3));
                new1.setHeader(resultSet.getString(4));
                new1.setPathImage(resultSet.getString(5));
                new1.setDate(resultSet.getDate(6));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, preparedStatement);
        }
        return new1;
    }

    @Override
    public boolean delete(New entity) {
        boolean isDelete = false;
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = connection.prepareStatement("DELETE FROM NEW WHERE ID=?");
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
    public New create(New entity) {
        New new1 = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO NEW(H1, TEXT, HEADER, PATHIMAGE, DATE1) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, entity.getH1());
            preparedStatement.setString(2, entity.getText());
            preparedStatement.setString(3, entity.getHeader());
            preparedStatement.setString(4, entity.getPathImage());
            preparedStatement.setDate(5, (Date) entity.getDate());

            preparedStatement.executeUpdate();
            new1 = entity;
        } catch (Exception e) {

            throw new DaoException(e);
        } finally {
            daoHelper.close(preparedStatement);
        }
        return new1;
    }

    @Override
    public New update(New entity) {
        New new1 = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE NEW SET H1=?,TEXT=?,HEADER=?,PATHIMAGE=?,DATE1 =? WHERE id=?");
            preparedStatement.setInt(6, entity.getId());
            preparedStatement.setString(1, entity.getH1());
            preparedStatement.setString(2, entity.getText());
            preparedStatement.setString(3, entity.getHeader());
            preparedStatement.setString(4, entity.getPathImage());
            preparedStatement.setDate(5, (Date) entity.getDate());
            preparedStatement.executeUpdate();
            new1 = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(preparedStatement);
        }
        return new1;
    }
}
