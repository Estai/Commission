package com.epam.service;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Subject;

import java.util.List;

public class SubjectService {
    public List<Subject> findAll(){
        List<Subject> subjects = (List<Subject>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getSubjectDao().findAll();
            }
        });
        return subjects;
    }

    public Subject findById(final Integer id) {
        Subject subject = (Subject) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getSubjectDao().findById(id);
            }
        });
        return subject;
    }
    public List<Subject> findMainSubject() {
        List<Subject> subjects = (List<Subject>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getSubjectDao().findMainSubject();
            }
        });
        return subjects;
    }
}
