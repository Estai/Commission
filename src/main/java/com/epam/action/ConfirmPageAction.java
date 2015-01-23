package com.epam.action;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String groupParameter = req.getParameter("group");
        final int group_id = Integer.parseInt(groupParameter);
        req.setAttribute("message","Вы действительно хотите подать заявку на зачисление в группу: ");
        Group group = (Group) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getGroupDao().findById(group_id);
            }
        });
        req.setAttribute("group",group);
        return new ActionResult("confirm");
    }
}
