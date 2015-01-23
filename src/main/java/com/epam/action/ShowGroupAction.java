package com.epam.action;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class ShowGroupAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String faculty = req.getParameter("faculty");
        final int id_faculty = Integer.parseInt(faculty);
        List<Group> groups = (List<Group>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getGroupDao().findByFaculty(id_faculty);
            }
        });
        req.setAttribute("groups", groups);
        return new ActionResult("comission");
    }
}
