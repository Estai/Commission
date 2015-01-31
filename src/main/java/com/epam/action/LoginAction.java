package com.epam.action;

import com.epam.dao.*;
import com.epam.entity.Enrollee;
import com.epam.entity.Role;
import com.epam.entity.User;
import com.epam.pool.ConnectionPool;
import com.epam.service.EnrolleeService;
import com.epam.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LoginAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        final String login = req.getParameter("log");
        final String password = req.getParameter("pass");
        UserService userService=new UserService();
        EnrolleeService enrolleeService=new EnrolleeService();
        User user = userService.findByCredentials(login, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            Enrollee enrollee = enrolleeService.findByUser(user.getId());
            if(enrollee!=null) req.getSession().setAttribute("enrollee",enrollee);
//            if (user.getRole().equals(Role.ADMIN)) {
//
//                return new ActionResult("admin/admin");
//            }
//            if (req.getSession().getAttribute("registered")!=null) {
//                return new ActionResult("infoenr");
//            }
            return new ActionResult("index",true);
        } else {
            req.setAttribute("credentialsError", "login or password incorrect");
            req.setAttribute("log", login);
            return new ActionResult("registration");
        }
    }
}
