package com.epam.listener;

import com.epam.pool.ConnectionPool;
import com.epam.pool.PoolException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.sql.SQLException;
import java.util.ResourceBundle;

@WebListener()
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER= Logger.getLogger(ContextListener.class);
    public void contextInitialized(ServletContextEvent sce) {
        ResourceBundle bundle=ResourceBundle.getBundle("db");
        ConnectionPool pool = ConnectionPool.instance();

        pool.setParam(bundle.getString("driverName"), bundle.getString("url"), bundle.getString("username"), bundle.getString("pass"),Integer.parseInt(bundle.getString("connectionNumber")));
        pool.initConnection();
//        try {
//            pool.initConnection();
//        } catch (SQLException e) {
//            LOGGER.error(e);
//            throw new PoolException(e);
//        }

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }


}
