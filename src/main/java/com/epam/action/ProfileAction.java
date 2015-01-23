package com.epam.action;

import com.epam.entity.Enrollee;
import com.epam.entity.StatementEntity;
import com.epam.service.StatementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProfileAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Enrollee enrollee=(Enrollee)req.getSession().getAttribute("enrollee");
        if(enrollee!=null)
        {
            StatementService service=new StatementService();
            List<StatementEntity> listStatement = service.findByIdEnrollee(enrollee.getId());
            req.setAttribute("listStatement",listStatement);
        }
        return new ActionResult("profile");
    }
}
