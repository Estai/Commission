package com.epam.service;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Score;

import java.util.List;

public class ScoreService {
    public List<Score> findAll(){
        List<Score> scores = (List<Score>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getScoreDao().findAll();
            }
        });
        return scores;
    }
    public Score findById(final Integer id)
    {
        Score score = (Score) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getScoreDao().findById(id);
            }
        });
        return score;
    }
}
