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

public class ShowPageRegistrationEnrollee implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
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
            return new ActionResult("infoenrolle");
        }

        return new ActionResult("registration");
    }
}
