package com.epam.service;

import com.epam.manager.Helper;
import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Faculty;
import com.epam.entity.Group;

import java.util.List;

public class FacultyService {
    DaoManager daoManager = DaoFactory.getDaoFactory("jdbc").createDaoManager();
    public List<Faculty> findAll(){

        List<Faculty> faculties = (List<Faculty>) daoManager.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getFacultyDao().findAll();
            }
        });
        for (Faculty faculty:faculties){
            List<Group> groups = Helper.getInstance().getGroupService().findByIdFaculty(faculty.getId());
            if(!groups.isEmpty()){
            faculty.add(groups);
            }
        }

        return faculties;

    }

    public void delete(final Faculty entity) {
        daoManager.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getFacultyDao().delete(entity);
            }
        });
        List<Group> groups = Helper.getInstance().getGroupService().findByIdFaculty(entity.getId());
        if(!groups.isEmpty()) {
            for (Group group : groups) {
                Helper.getInstance().getGroupService().delete(group);
            }
        }
    }

    public Faculty findById(final Integer id) {
        Faculty faculty = (Faculty) daoManager.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getFacultyDao().findById(id);
            }
        });
        List<Group> groups = Helper.getInstance().getGroupService().findByIdFaculty(faculty.getId());
        if(!groups.isEmpty()){
            faculty.add(groups);
        }
        return faculty;
    }

    public Faculty create(final Faculty entity) {
        Faculty faculty = (Faculty) daoManager.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getFacultyDao().create(entity);
            }
        });
        return faculty;
    }

    public boolean findByName(final String name) {
        return (Boolean)daoManager.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getFacultyDao().findByName(name);
            }
        });

    }
}
