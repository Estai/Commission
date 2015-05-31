package com.epam.action;

import com.epam.manager.ApplicationManager;
import com.epam.manager.Helper;
import com.epam.entity.Application;
import com.epam.entity.PriorityStatement;
import com.epam.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class DeleteApplicationAction implements Action {

    ApplicationService applicationService= Helper.getInstance().getApplicationService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Application> applications = (List<Application>) req.getSession().getAttribute("applications");
        List<Application> deleteApplications=new ArrayList<>();
        for (Application application:applications)
        {
            if(req.getParameter(String.valueOf(application.getId()))!=null)
            {
                applicationService.delete(application);
                deleteApplications.add(application);
            }
        }
        applications.removeAll(deleteApplications);
        for (Application application:applications) {
            PriorityStatement priority = ApplicationManager.getPriority(applications.indexOf(application));
            application.setPriority(priority);
            applicationService.update(application);
        }

        return new ActionResult("profile");
    }
}
