package com.epam.action;

import com.epam.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFaculty implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String[] faculties = req.getParameterValues("faculty");
        if(faculties!=null)
        {
            for (String idFaculty:faculties) {
                int id = Integer.parseInt(idFaculty);
                Helper.getInstance().getFacultyService().delete(id);
            }
         req.setAttribute("Delete", "Удалено");
        }
        return new ActionResult("admin/faculty");
    }
}
