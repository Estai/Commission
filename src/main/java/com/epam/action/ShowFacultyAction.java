package com.epam.action;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.dao.FacultyDao;
import com.epam.entity.Faculty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowFacultyAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Faculty> faculties = (List<Faculty>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getFacultyDao().findAll();
            }
        });
        req.getSession().setAttribute("faculties", faculties);
        return new ActionResult("comission");
    }
}
