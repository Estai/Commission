package com.epam.dao;

import com.epam.pool.ConnectionPool;
import com.epam.pool.PoolException;

import java.sql.Connection;

public abstract class DaoFactory {
    public abstract DaoManager createDaoManager();

    public abstract void releaseConnections();

    public static DaoFactory getDaoFactory(String whichFactory) {
        switch (whichFactory.toUpperCase()) {
            case "JDBC":
                return new JdbcDaoFactory();
            default:
                throw new DaoException("Dao not found");
        }

    }


}
