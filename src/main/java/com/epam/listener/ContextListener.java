package com.epam.listener;

import com.epam.manager.Helper;
import com.epam.manager.LocaleManager;
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
        int[] days={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        sce.getServletContext().setAttribute("days", days);
        int[] years=new int[43];
        for (int i = 1958; i < 2001; i++) years[i-1958]=i;
        sce.getServletContext().setAttribute("years",years);
        sce.getServletContext().setAttribute("numberSubject", numberSubject);

        SubjectService subjectService = Helper.getInstance().getSubjectService();
        List<Subject> subjects = subjectService.findAll();
        sce.getServletContext().setAttribute("subjects", subjects);

        ScoreService scoreService = Helper.getInstance().getScoreService();
        List<Score> scores = scoreService.findAll();
        sce.getServletContext().setAttribute("scores", scores);

        GroupService groupService = Helper.getInstance().getGroupService();
        List<Group> groups = groupService.findAll();
        sce.getServletContext().setAttribute("Allgroups", groups);

        FacultyService facultyService = Helper.getInstance().getFacultyService();
        List<Faculty> faculties = facultyService.findAll();
        sce.getServletContext().setAttribute("faculties", faculties);

        HashMap<String, Locale> locales = LocaleManager.getInstance().getLocales();
        sce.getServletContext().setAttribute("locales", locales);
//        CreatorDir.create(resourceBundle.getString("directoryFileStorage"));

//        ServletContext ctx = sce.getServletContext();
//        String relativePath = ctx.getInitParameter("tempfile.dir");
//        File file = new File("/static/www/file" + File.separator + relativePath);
//        if(!file.exists()) file.mkdirs();
//        System.out.println("File Directory created to be used for storing files"+"/static/www/file"+ File.separator + relativePath);
//        ctx.setAttribute("FILES_DIR_FILE", file);
//        ctx.setAttribute("FILES_DIR", "/static/www/file" + File.separator + relativePath);


    }

    public void contextDestroyed(ServletContextEvent sce) {
        pool.shutdown();
    }


}
