package com.epam.action;

import com.epam.manager.Helper;
import com.epam.entity.Faculty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FacultyAdminAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Faculty> faculties = Helper.getInstance().getFacultyService().findAll();
        req.setAttribute("faculties",faculties);
        return new ActionResult("admin/faculty");
    }
}
