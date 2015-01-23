package com.epam.pool;

import com.sun.java.util.jar.pack.*;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ConnectionPool {
    private static final ConnectionPool POOL = new ConnectionPool();
    private static final Logger LOGGER= Logger.getLogger(ConnectionPool.class);
    private String driverName;
    private String url;
    private String username;
    private String pass;
    private int connectionNumber;
    private LinkedList<PooledConnection> connections;
    private Semaphore semaphore;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setConnectionNumber(int connectionNumber) {
        this.connectionNumber = connectionNumber;
    }

    public void setParam(String driverName, String url, String username, String pass, int connectionNumber) {
        this.driverName = driverName;
        this.url = url;
        this.username = username;
        this.pass = pass;
        this.connectionNumber = connectionNumber;

    }

    public void initConnection() {
        try {
            Class.forName(driverName);
        }catch (ClassNotFoundException e){
            LOGGER.error("Class not found "+ e);
            throw new PoolException(e);
        }
      try  { semaphore = new Semaphore(connectionNumber);
            connections = new LinkedList<PooledConnection>();
            for (int i = 0; i < connectionNumber; i++) {
                Connection connection = DriverManager.getConnection(url, username, pass);
                PooledConnection pooledConnection = new PooledConnection(connection, this);
                connections.add(pooledConnection);
            }
        } catch (SQLException e) {
                 LOGGER.error("Class not found "+ e);
          throw new PoolException(e);
        }

    }

    public static ConnectionPool instance() {
        return POOL;

    }

    public Connection getConnection() {
        PooledConnection pooledConnection = null;
        try {
            semaphore.acquire();
            pooledConnection = connections.remove(0);
            return pooledConnection;
        } catch (InterruptedException e) {

        }

        return pooledConnection;
    }

    public void realese(PooledConnection connection) {
        int release = connectionNumber - semaphore.availablePermits();
        connections.add(connection);
        semaphore.release(release);
    }
}
