package com.epam.dao;

import com.epam.entity.Score;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcScoreDao implements ScoreDao {
    private Connection connection;
    private DaoHelper daoHelper;

    public JdbcScoreDao(Connection connection) {
        this.connection = connection;
        daoHelper = new DaoHelper();
    }

    @Override
    public List<Score> findAll() {
        List<Score> scores = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SCORE;");
            while (resultSet.next()) {
                Score score = new Score();
                score.setId(resultSet.getInt(1));
                score.setScore(resultSet.getString(2));
                scores.add(score);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoHelper.close(resultSet, statement);
        }
        return scores;
    }

    @Override
    public Score findById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Score entity) {
        return false;
    }

    @Override
    public Score create(Score entity) {
        return null;
    }

    @Override
    public Score update(Score entity) {
        return null;
    }
}
