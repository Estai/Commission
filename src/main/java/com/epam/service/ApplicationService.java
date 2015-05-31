package com.epam.service;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Application;

import java.util.List;

public class ApplicationService {
    public List<Application> findByIdEnrollee(final Integer id){
        List<Application> applications = (List<Application>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getApplicationDao().findByIdEnrollee(id);
            }
        });
        return applications;
    }

    public boolean findByIdAndGroup(final int id, final String group) {
        Application application = (Application) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getApplicationDao().findByIdAndGroup(id, group);
            }
        });
        if(application!=null) {return true;}
        return false;
    }

    public Application create(final Application entity) {
        Application application = (Application)DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getApplicationDao().create(entity);
            }
        });
       return application;
    }

    public void delete(final Application application) {
        DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getApplicationDao().delete(application);
            }
        });
    }

    public void update(final Application application) {
        DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getApplicationDao().update(application);
            }
        });
    }

    public List<Application> findAll() {
        List<Application> applications = (List<Application>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getApplicationDao().findAll();
            }
        });
        for (Application application:applications)
        {

        }
        return applications;
    }
}
