package com.epam.listener;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Faculty;
import com.epam.entity.Group;
import com.epam.entity.Score;
import com.epam.entity.Subject;
import com.epam.pool.ConnectionPool;;
import com.epam.service.FacultyService;
import com.epam.service.GroupService;
import com.epam.service.ScoreService;
import com.epam.service.SubjectService;
import org.apache.log4j.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@WebListener()
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER= Logger.getLogger(ContextListener.class);
    ResourceBundle bundle=ResourceBundle.getBundle("db");
    ResourceBundle resourceBundle=ResourceBundle.getBundle("app");
    ConnectionPool pool = ConnectionPool.instance();
    SubjectService subjectService;
    ScoreService scoreService;
    FacultyService facultyService;
    GroupService groupService;

    public void contextInitialized(ServletContextEvent sce) {
        pool.setParam(bundle.getString("driverName"), bundle.getString("url"), bundle.getString("username"), bundle.getString("pass"),Integer.parseInt(bundle.getString("connectionNumber")));
        pool.initConnection();

        List<Integer> numberSubject=new ArrayList<>();
        for (int i = 0; i <Integer.parseInt(resourceBundle.getString("numberSubject")) ; i++) {
            numberSubject.add(i+1);
        }
        sce.getServletContext().setAttribute("numberSubject", numberSubject);
        subjectService=new SubjectService();
        List<Subject> subjects = subjectService.findAll();
        sce.getServletContext().setAttribute("subjects", subjects);

        scoreService=new ScoreService();
        List<Score> scores = scoreService.findAll();
        sce.getServletContext().setAttribute("scores", scores);

        groupService=new GroupService();
        List<Group> groups = groupService.findAll();
        sce.getServletContext().setAttribute("groups", groups);

        facultyService=new FacultyService();
        List<Faculty> faculties = facultyService.findAll();
        sce.getServletContext().setAttribute("faculties", faculties);
//        try {
//            pool.initConnection();
//        } catch (SQLException e) {
//            LOGGER.error(e);
//            throw new PoolException(e);
//        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
        pool.shutdown();
    }


}
