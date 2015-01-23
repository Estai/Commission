package com.epam.dao;


import com.epam.entity.Role;
import com.epam.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private Connection connection;
    private DaoHelper daoHelper;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
        daoHelper = new DaoHelper();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM USER;");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setLogin(resultSet.getString(3));
                user.setRole(Role.valueOf(resultSet.getString(4).toUpperCase()));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, statement);
        }
        return users;
    }


    @Override
    public User findById(Integer id) {
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(Role.valueOf(resultSet.getString(4).toUpperCase()));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, preparedStatement);
        }
        return user;
    }

    @Override
    public boolean delete(User entity) {
        boolean isDelete = false;
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = connection.prepareStatement("DELETE FROM USER WHERE ID=?");
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
    public User create(User entity) {
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO USER(LOGIN, PASS) VALUES(?,?)");
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            }
            user = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(generatedKeys, preparedStatement);
        }
        return user;
    }

    @Override
    public User update(User entity) {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE USER SET LOGIN=?,PASS=? WHERE id=?");
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.executeUpdate();
            user = entity;
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(preparedStatement);
        }
        return user;
    }

    @Override
    public User findByCredentials(String login, String password) {
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE LOGIN=? AND PASS=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
            }
        } catch (Exception e) {
            throw new DaoException(e);

        } finally {
            daoHelper.close(resultSet, preparedStatement);
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE LOGIN=?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(Role.valueOf(resultSet.getString(4)));


            }
        } catch (Exception e) {
            throw new DaoException(e);

        } finally {
            daoHelper.close(resultSet, preparedStatement);
        }
        return user;
    }

}
