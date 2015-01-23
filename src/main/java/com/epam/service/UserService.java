package com.epam.service;


import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.User;

public class UserService {

  public boolean findByLogin(final String login){
        User user = (User) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getUserDao().findByLogin(login);
            }
        });
      if (user != null) {
         return true;
      }
return false;
    }
    public boolean create(final User entity){
        User user = (User) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getUserDao().create(entity);
            }
        });
        if (user != null) {
            return true;
        }
        return false;
    }

    public User findByCredentials(final String login, final String password){

        User user = (User) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getUserDao().findByCredentials(login, password);
            }
        });

        return user;
    }

    public User update(final User entity) {
        User user = (User) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getUserDao().update(entity);
            }
        });
        return user;
    }
}
