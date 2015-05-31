package com.epam.service;

import com.epam.manager.Helper;
import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Group;
import com.epam.entity.Subject;

import java.util.List;

public class GroupService {
    DaoManager daoManager = DaoFactory.getDaoFactory("jdbc").createDaoManager();
    public List<Group> findAll(){

        List<Group> groups = (List<Group>) daoManager.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getGroupDao().findAll();
            }
        });
        for (Group group :groups) {
            Subject subject = Helper.getInstance().getSubjectService().findById(group.getProfileSubject().getId());
            group.setProfileSubject(subject);
        }

        return groups;

    }

    public Group findById(final Integer id) {
        Group group = (Group) daoManager.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getGroupDao().findById(id);
            }
        });
        Subject subject = Helper.getInstance().getSubjectService().findById(group.getProfileSubject().getId());
        group.setProfileSubject(subject);
        return group;
    }
   public List<Group> findByIdFaculty(final Integer id){
       List<Group> groups = (List<Group>) daoManager.executeAndClose(new DaoCommand() {
           @Override
           public Object execute(DaoManager daoManager) {
               return daoManager.getGroupDao().findByFaculty(id);
           }
       });
       for (Group group :groups) {
           Subject subject = Helper.getInstance().getSubjectService().findById(group.getProfileSubject().getId());
           group.setProfileSubject(subject);
       }
       return groups;
   }

    public void delete(final Group group) {
        daoManager.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getGroupDao().delete(group);
            }
        });
    }

    public Group create(final Group entity) {
        Group group = (Group) daoManager.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getGroupDao().create(entity);
            }
        });
        return group;
    }
}