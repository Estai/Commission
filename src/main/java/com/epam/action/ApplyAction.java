package com.epam.action;

import com.epam.Helper;
import com.epam.entity.*;
import com.epam.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApplyAction implements Action {
    ActionResult againPage=new ActionResult("comission");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        Enrollee enrollee = (Enrollee) req.getSession().getAttribute("enrollee");
        List<Group> groupsApplication = (List<Group>) req.getSession().getAttribute("application");
        ApplicationService service= Helper.getInstance().getApplicationService();

        for(Group group:groupsApplication)
        {
            boolean isApplication = service.findByIdAndGroup(enrollee.getId(), group.getName());
               if(isApplication){req.setAttribute("DataError","Вы уже подали заявку на эту специальность: "+group.getName());
                   return againPage;}
            Iterator<Map.Entry<Subject, Score>> iterator = enrollee.getScore().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Subject, Score> next = iterator.next();
                if(!next.getKey().isMain()) {
                    if (!group.getProfileSubject().getName().equalsIgnoreCase(next.getKey().getName())) {
                        req.setAttribute("DataError", group.getName() + " профильный предмет-" + group.getProfileSubject().getName());
                        return againPage;
                    }
                }
            }
        }

        PriorityStatement priorityStatement;
        List<Application> applications = service.findByIdEnrollee(enrollee.getId());
        for(Group group:groupsApplication){
            switch (applications.size()){
                case 0: priorityStatement=PriorityStatement.FIRST; break;
                case 1: priorityStatement=PriorityStatement.SECOND; break;
                case 2: priorityStatement=PriorityStatement.THIRD; break;
                case 3: priorityStatement=PriorityStatement.FOURTH; break;
                case 4: priorityStatement=PriorityStatement.FIFTH; break;
                default:{ req.setAttribute("DataError","Превышает лимит");return againPage; }
            }
            Application application = new Application();
            application.setIdEnrollee(enrollee.getId());
            application.setGroupName(group.getName());
            application.setPriority(priorityStatement);
            service.create(application);
            applications.add(application);
        }
        req.setAttribute("success","Заявка принята");
        req.getSession().setAttribute("applications",applications);

        return againPage;
    }
}
