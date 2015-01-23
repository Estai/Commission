package com.epam.dao;


import com.epam.entity.User;
import com.epam.pool.ConnectionPool;
import com.epam.pool.PoolException;

import java.sql.Connection;

public class JdbcDaoFactory extends DaoFactory {
    private static ConnectionPool pool = ConnectionPool.instance();
    private Connection connection = createConnection();

    public Connection createConnection() {
        Connection con = null;
        try {
            con = pool.getConnection();

        } catch (PoolException e) {
            throw new DaoException(e);
        }
        return con;

    }

    public Connection getConnection() {
        return connection;
    }

    public DaoManager createDaoManager() {
        DaoManager daoManager = new JdbcDaoManager(connection);
        return daoManager;
    }

    @Override
    public void releaseConnections() {

    }
}
