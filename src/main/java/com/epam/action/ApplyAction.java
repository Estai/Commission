package com.epam.action;

import com.epam.entity.PriorityStatement;
import com.epam.entity.StatementEntity;
import com.epam.service.StatementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ApplyAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String group = req.getParameter("id_group");
        String id_enrollee = req.getParameter("id_enrollee");
        StatementService service=new StatementService();
        List<StatementEntity> statements = service.findByIdEnrollee(Integer.parseInt(id_enrollee));
        PriorityStatement priorityStatement;
        boolean statement = service.findByIdAndGroup(Integer.parseInt(id_enrollee), group);
        if(statement){
            req.setAttribute("DataError","Вы уже подали заявку на эту специальность");
            return new ActionResult("comission");
        }
        switch (statements.size()){
             case 0: priorityStatement=PriorityStatement.FIRST; break;
             case 1: priorityStatement=PriorityStatement.SECOND; break;
             case 2: priorityStatement=PriorityStatement.THIRD; break;
             case 3: priorityStatement=PriorityStatement.FOURTH; break;
             case 4: priorityStatement=PriorityStatement.FIFTH; break;
            default:{ req.setAttribute("Error","Превышает л");return new ActionResult("comission"); }
         }
       StatementEntity statementEntity=new StatementEntity();
        statementEntity.setIdEnrollee(Integer.parseInt(id_enrollee));
        statementEntity.setGroupName(group);
        statementEntity.setPriority(priorityStatement);
        service.create(statementEntity);
        return new ActionResult("comission");
    }
}
