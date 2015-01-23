package com.epam.dao;

import com.epam.entity.Score;
import com.epam.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public interface DaoManager {
    EnrolleeDao getEnrolleeDao();

    FacultyDao getFacultyDao();

    StatementDao getStatementDao();

    SubjectDao getSubjectDao();

    UserDao getUserDao();

    NewDao getNewDao();

    GroupDao getGroupDao();

    ScoreDao getScoreDao();

    Object executeAndClose(DaoCommand command);

    Object transaction(DaoCommand command) throws SQLException;

    Object transactionAndClose(DaoCommand command);

}
