package com.epam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface Action {
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp);
}
