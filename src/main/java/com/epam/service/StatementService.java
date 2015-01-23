package com.epam.service;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.StatementEntity;

import java.util.List;

public class StatementService {
    public List<StatementEntity> findByIdEnrollee(final Integer id){
        List<StatementEntity> entityList = (List<StatementEntity>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getStatementDao().findByIdEnrollee(id);
            }
        });
        return entityList;
    }

    public boolean findByIdAndGroup(final int id, final String group) {
        StatementEntity statementEntity = (StatementEntity) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getStatementDao().findByIdAndGroup(id, group);
            }
        });
        if(statementEntity!=null) {return true;}
        return false;
    }

    public StatementEntity create(final StatementEntity entity) {
        StatementEntity statementEntity = (StatementEntity)DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getStatementDao().create(entity);
            }
        });
       return statementEntity;
    }
}
