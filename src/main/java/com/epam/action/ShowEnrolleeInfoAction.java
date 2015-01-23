package com.epam.action;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Score;
import com.epam.entity.Subject;
import com.epam.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowEnrolleeInfoAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameter("group")!=null) {
            String group = req.getParameter("group");
            int group_id = Integer.parseInt(group);
            req.getSession().setAttribute("group_id", group_id);
        }
        User user = (User) req.getSession().getAttribute("user");



        List<Subject> subjects = (List<Subject>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getSubjectDao().findAll();
            }
        });
        req.getSession().setAttribute("subjects", subjects);

        List<Score> scores = (List<Score>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getScoreDao().findAll();
            }
        });
        req.getSession().setAttribute("scores", scores);
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i+1);
        }
        req.getSession().setAttribute("lst", list);
        if (user != null) {
            return new ActionResult("infoenr");
        }
        req.getSession().setAttribute("registered", "1");
        req.setAttribute("reg", "Зарегистрируйтесь, пожалуйста,для приемной комиссии");
        return new ActionResult("registration");
    }
}
