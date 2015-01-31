package com.epam.action;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Faculty;
import com.epam.entity.Group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ConfirmPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Group> groups = (List<Group>) req.getServletContext().getAttribute("groups");
        List<Group> application=new ArrayList<>();
        for(Group group:groups){
            if(req.getParameter(group.getName())!=null)
            {
                application.add(group);
            }
        }
//        String groupParameter = req.getParameter("group");

//        final int group_id = Integer.parseInt(groupParameter);
        req.getSession().setAttribute("message","Вы действительно хотите подать заявку на зачисление в группу: ");
//        Group group = (Group) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
//            @Override
//            public Object execute(DaoManager daoManager) {
//                return daoManager.getGroupDao().findById(group_id);
//            }
//        });
        req.getSession().setAttribute("application",application);
        return new ActionResult("confirm",true);
    }
}
