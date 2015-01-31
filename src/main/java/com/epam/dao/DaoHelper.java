package com.epam.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoHelper {

    public static void close(ResultSet resultSet, Statement statement) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static void  close(Statement statement) {
        try {
            if (statement != null) statement.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
