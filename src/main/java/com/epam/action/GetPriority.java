package com.epam.action;

import com.epam.entity.Application;
import com.epam.entity.PriorityStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetPriority implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Application> applications = (List<Application>) req.getSession().getAttribute("applications");
        List<PriorityStatement> priorityStatements=new ArrayList<>();
        for (Application application :applications) {
            if(application.getId()==id){
                req.setAttribute("application",application);
            }
            priorityStatements.add(application.getPriority());
        }
       req.setAttribute("priorities", priorityStatements);
        return new ActionResult("updatePage");
    }
}
