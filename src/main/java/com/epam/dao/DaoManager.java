package com.epam.dao;

import java.sql.SQLException;

public interface DaoManager {
    EnrolleeDao getEnrolleeDao();

    FacultyDao getFacultyDao();

    ApplicationDao getApplicationDao();

    SubjectDao getSubjectDao();

    UserDao getUserDao();

    NewDao getNewDao();

    GroupDao getGroupDao();

    ScoreDao getScoreDao();

    Object executeAndClose(DaoCommand command);

    Object transaction(DaoCommand command) throws SQLException;

    Object transactionAndClose(DaoCommand command);

}
