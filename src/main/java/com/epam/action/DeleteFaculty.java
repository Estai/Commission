package com.epam.action;

import com.epam.Helper;
import com.epam.entity.Faculty;
import com.epam.entity.Group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteFaculty implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Faculty> faculties = (List<Faculty>) req.getServletContext().getAttribute("faculties");
        String[] parameter = req.getParameterValues("faculty");
        if(parameter!=null)
        {
            for (String idFaculty:parameter) {
                int id = Integer.parseInt(idFaculty);
                Faculty faculty = Helper.getInstance().getFacultyService().findById(id);
                Helper.getInstance().getFacultyService().delete(faculty);

            }
         req.setAttribute("Delete", "Удалено");
        }
        List<Faculty> all = Helper.getInstance().getFacultyService().findAll();
        req.getServletContext().setAttribute("faculties",all);
        return new ShowPageAction("admin/faculty").execute(req,resp);
    }
}
