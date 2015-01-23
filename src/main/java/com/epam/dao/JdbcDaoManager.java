package com.epam.dao;

import com.epam.entity.Score;
import com.epam.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoManager implements DaoManager {
    private Connection connection;

    public JdbcDaoManager(Connection connection) {
        this.connection = connection;
    }

    public Object executeAndClose(DaoCommand command) {
        try {
            return command.execute(this);
        } finally {
            try {
                this.connection.close();

            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public Object transaction(DaoCommand command) throws SQLException {
        try {
            this.connection.setAutoCommit(false);
            Object object = command.execute(this);
            this.connection.commit();
            return object;
        } catch (Exception e) {
            this.connection.rollback();
            throw new DaoException(e);
        } finally {
            this.connection.setAutoCommit(true);
        }
    }

    @Override
    public Object transactionAndClose(final DaoCommand command) {
        return executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                try {
                    return daoManager.transaction(command);
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        });
    }


    @Override
    public EnrolleeDao getEnrolleeDao() {
        if (connection == null) {
            throw new DaoException("no connection");
        }
        return new JdbcEnrolleeDao(connection);
    }

    @Override
    public FacultyDao getFacultyDao() {
        if (connection == null) {
            throw new DaoException("no connection");
        }
        return new JdbcFacultyDao(connection);

    }

    @Override
    public StatementDao getStatementDao() {
        if (connection == null) {
            throw new DaoException("no connection");
        }
        return new JdbcStatementDao(connection);
    }

    @Override
    public SubjectDao getSubjectDao() {
        if (connection == null) {
            throw new DaoException("no connection");
        }
        return new JdbcSubjectDao(connection);
    }

    @Override
    public UserDao getUserDao() {
        if (connection == null) {
            throw new DaoException("no connection");
        }
        return new JdbcUserDao(connection);
    }

    @Override
    public NewDao getNewDao() {
        if (connection == null) {
            throw new DaoException("no connection");
        }
        return new JdbcNewDao(connection);
    }

    @Override
    public GroupDao getGroupDao() {
        if (connection == null) {
            throw new DaoException("no connection");
        }
        return new JdbcGroupDao(connection);
    }

    @Override
    public ScoreDao getScoreDao() {
        if (connection == null) {
            throw new DaoException("no connection");
        }
        return new JdbcScoreDao(connection);
    }


}
