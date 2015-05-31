package com.epam.action;

import com.epam.manager.Helper;
import com.epam.entity.Application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Application> Allapplications = Helper.getInstance().getApplicationService().findAll();
        req.setAttribute("Allapplications",Allapplications);
        return new ActionResult("admin/admin");
    }
}
