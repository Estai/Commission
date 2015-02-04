package com.epam.listener;

import com.epam.Helper;
import com.epam.LocaleManager;
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
import java.util.*;

@WebListener()
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER= Logger.getLogger(ContextListener.class);
    ResourceBundle bundle=ResourceBundle.getBundle("db");
    ResourceBundle resourceBundle=ResourceBundle.getBundle("app");
    ConnectionPool pool = ConnectionPool.instance();

    public void contextInitialized(ServletContextEvent sce) {
        pool.setParam(bundle.getString("driverName"), bundle.getString("url"), bundle.getString("username"), bundle.getString("pass"),Integer.parseInt(bundle.getString("connectionNumber")));
        pool.initConnection();

        List<Integer> numberSubject=new ArrayList<>();
        for (int i = 0; i <Integer.parseInt(resourceBundle.getString("numberSubject")) ; i++) {
            numberSubject.add(i+1);
        }
        sce.getServletContext().setAttribute("numberSubject", numberSubject);

        SubjectService subjectService = Helper.getInstance().getSubjectService();
        List<Subject> subjects = subjectService.findAll();
        sce.getServletContext().setAttribute("subjects", subjects);

        ScoreService scoreService = Helper.getInstance().getScoreService();
        List<Score> scores = scoreService.findAll();
        sce.getServletContext().setAttribute("scores", scores);

//        GroupService groupService = Helper.getInstance().getGroupService();
//        List<Group> groups = groupService.findAll();
//        sce.getServletContext().setAttribute("groups", groups);

        FacultyService facultyService = Helper.getInstance().getFacultyService();
        List<Faculty> faculties = facultyService.findAll();
        sce.getServletContext().setAttribute("faculties", faculties);

        HashMap<String, Locale> locales = LocaleManager.getInstance().getLocales();
        sce.getServletContext().setAttribute("locales", locales);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        pool.shutdown();
    }


}
