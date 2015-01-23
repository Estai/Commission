package com.epam.service;

import com.epam.dao.*;
import com.epam.entity.Enrollee;
import com.epam.entity.Score;
import com.epam.entity.Subject;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

public class EnrolleeService {

    public Enrollee create(final Enrollee entity, final Map<Integer,Integer> scoreMap) throws SQLException {

        Enrollee enrollee =(Enrollee) DaoFactory.getDaoFactory("jdbc").createDaoManager().transactionAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                Enrollee localEnrollee = daoManager.getEnrolleeDao().create(entity);
                Set<Integer> subjects = scoreMap.keySet();
                for (Integer subject : subjects) {
                    daoManager.getEnrolleeDao().insertScore(entity.getId(), subject, scoreMap.get(subject));
                }
                return  localEnrollee;

            }
        });

return enrollee;
    }

    public Enrollee findByUser(final Integer idUser ) {
        Enrollee enrollee = (Enrollee) DaoFactory.getDaoFactory("jdbc").createDaoManager().transactionAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {

                return daoManager.getEnrolleeDao().findByUser(idUser);
            }


        });
   return enrollee;
    }
}
