package com.epam.action;

import com.epam.manager.Helper;
import com.epam.entity.Enrollee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowEnrollee implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Enrollee> enrollees = Helper.getInstance().getEnrolleeService().findAll();
        req.setAttribute("enrollees",enrollees);
        return new ActionResult("admin/enrollee");
    }
}
