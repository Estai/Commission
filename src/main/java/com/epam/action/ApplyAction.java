package com.epam.action;

import com.epam.entity.Group;
import com.epam.entity.PriorityStatement;
import com.epam.entity.Application;
import com.epam.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ApplyAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
//        String group = req.getParameter("id_group");
        String id_enrollee = req.getParameter("id_enrollee");
        List<Group> groupsApplication = (List<Group>) req.getSession().getAttribute("application");
        ApplicationService service=new ApplicationService();
        List<Application> applications = service.findByIdEnrollee(Integer.parseInt(id_enrollee));
        for(Group group:groupsApplication)
        {
            boolean isApplication = service.findByIdAndGroup(Integer.parseInt(id_enrollee), group.getName());
               if(isApplication){req.setAttribute("DataError","Вы уже подали заявку на эту специальность: "+group.getName());
            return new ActionResult("comission");}
        }

        PriorityStatement priorityStatement;


//       // if(statement){
//            req.setAttribute("DataError","Вы уже подали заявку на эту специальность");
//            return new ActionResult("comission");
//        }
        for(Group group:groupsApplication){
            switch (applications.size()){
                case 0: priorityStatement=PriorityStatement.FIRST; break;
                case 1: priorityStatement=PriorityStatement.SECOND; break;
                case 2: priorityStatement=PriorityStatement.THIRD; break;
                case 3: priorityStatement=PriorityStatement.FOURTH; break;
                case 4: priorityStatement=PriorityStatement.FIFTH; break;
                default:{ req.setAttribute("DataError","Превышает лимит");return new ActionResult("comission"); }
            }
            Application application = new Application();
            application.setIdEnrollee(Integer.parseInt(id_enrollee));
            application.setGroupName(group.getName());
            application.setPriority(priorityStatement);
            service.create(application);
            applications.add(application);
        }
        req.getSession().setAttribute("applications",applications);

        return new ActionResult("comission");
    }
}
