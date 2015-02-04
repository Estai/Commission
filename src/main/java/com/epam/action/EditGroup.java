package com.epam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditGroup implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String parameter = req.getParameter("id");
        if(parameter!=null)
        {
            int id = Integer.parseInt(parameter);
//            Hel
//            if()
        }
        return null;
    }
}
