package com.epam.action;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Enrollee;
import com.epam.entity.Application;
import com.epam.entity.Score;
import com.epam.entity.Subject;
import com.epam.service.ApplicationService;
import com.epam.service.EnrolleeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProfileAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Enrollee enrollee=(Enrollee)req.getSession().getAttribute("enrollee");
        EnrolleeService enrolleeService=new EnrolleeService();
        if(enrollee!=null)
        {
            ApplicationService service=new ApplicationService();
            List<Application> applications = service.findByIdEnrollee(enrollee.getId());
            req.getSession().setAttribute("applications",applications);
            Map<Subject, Score> subjectWithScore = enrolleeService.getScore(enrollee.getId());
            Set<Map.Entry<Subject, Score>> subjectsMap = subjectWithScore.entrySet();
            req.getSession().setAttribute("subjectsMap",subjectsMap);

        }
        return new ActionResult("profile");
    }
}
