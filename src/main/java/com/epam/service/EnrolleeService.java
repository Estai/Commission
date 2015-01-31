package com.epam.service;

import com.epam.dao.*;
import com.epam.entity.Enrollee;
import com.epam.entity.Score;
import com.epam.entity.Subject;
import java.sql.SQLException;
import java.util.*;

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
    public Map<Subject,Score> getScore(final Integer id_enrollee){
        Map<Subject,Score> subjectScoreMap=new HashMap<>();
        DaoManager daoManager = DaoFactory.getDaoFactory("jdbc").createDaoManager();
        Map<Integer, Integer> scoreMap = (Map<Integer, Integer>) daoManager.executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getEnrolleeDao().getScore(id_enrollee);
            }
        });

        Set<Integer> subjects = scoreMap.keySet();
                for(final Integer id_subject:subjects)
                {
                    final Integer id_score = scoreMap.get(id_subject);
                    Score score = (Score) daoManager.executeAndClose(new DaoCommand() {
                        @Override
                        public Object execute(DaoManager daoManager) {
                            return daoManager.getScoreDao().findById(id_score);
                        }
                    });
                    Subject subject = (Subject) daoManager.executeAndClose(new DaoCommand() {
                        @Override
                        public Object execute(DaoManager daoManager) {
                            return daoManager.getSubjectDao().findById(id_subject);
                        }
                    });
                    subjectScoreMap.put(subject,score);
                }

        return subjectScoreMap;
    }

    public void updateScore(final Map<Subject, Score> parameter, final Integer id) {
        Enrollee enrollee = (Enrollee) DaoFactory.getDaoFactory("jdbc").createDaoManager().transactionAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                daoManager.getEnrolleeDao().deleteScore(id);
                Set<Map.Entry<Subject, Score>> scores = parameter.entrySet();
                for (Map.Entry<Subject, Score> score : scores) {
                    daoManager.getEnrolleeDao().insertScore(id, score.getKey().getId(), score.getValue().getId());
                }
                return null;
            }
        });
    }

    public Enrollee update(final Enrollee entity) {
        Enrollee enrollee = (Enrollee) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getEnrolleeDao().update(entity);
            }
        });
        return enrollee;
    }
}
