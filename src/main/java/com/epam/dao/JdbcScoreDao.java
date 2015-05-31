package com.epam.dao;

import com.epam.entity.Application;
import com.epam.entity.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcScoreDao implements ScoreDao {
    private Connection connection;

    public JdbcScoreDao(Connection connection) {
        this.connection = connection;
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
            DaoHelper.close(resultSet, statement);
        }
        return scores;
    }

    @Override
    public Score findById(Integer id) {

        Score score = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM SCORE WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                score = new Score();
                score.setId(resultSet.getInt(1));
                score.setScore(resultSet.getString(2));
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            DaoHelper.close(resultSet, preparedStatement);
        }
        return score;
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
