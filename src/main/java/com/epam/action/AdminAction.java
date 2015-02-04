package com.epam.action;

import com.epam.Helper;
import com.epam.entity.Application;
import com.epam.entity.Group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return new ActionResult("admin/admin");
    }
}
