package com.epam.dao;


import com.epam.entity.User;

public interface UserDao extends Dao<User> {
    public User findByCredentials(String login, String password);

    public User findByLogin(String login);
}
