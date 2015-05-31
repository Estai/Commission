package com.epam.action;

import com.epam.manager.Helper;
import com.epam.entity.Group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ConfirmPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String[] groupApplication  = req.getParameterValues("group");
        List<Group> application=new ArrayList<>();
        if(groupApplication!=null){
         for(String idGroup: groupApplication)
        {
            int id = Integer.parseInt(idGroup);
            Group group = Helper.getInstance().getGroupService().findById(id);
            application.add(group);
        }
            req.getSession().setAttribute("message","Вы действительно хотите подать заявку на зачисление в группу: ");
            req.getSession().setAttribute("application",application);
            return new ActionResult("confirm",true);
        }

        return new ActionResult("comission");
    }
}
