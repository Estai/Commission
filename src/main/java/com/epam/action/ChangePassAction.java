package com.epam.action;

import com.epam.entity.User;
import com.epam.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePassAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        final String login = req.getParameter("log");
        final String password = req.getParameter("pass");
        final String newPass = req.getParameter("newPass");
        UserService userService=new UserService();
        User user = userService.findByCredentials(login, password);
        if (user != null) {
            user.setPassword(newPass);
            User updateUser = userService.update(user);
            req.getSession().setAttribute("user",updateUser);
            req.getSession().setAttribute("success","Пароль был изменен");
            return new ActionResult("profile");
        }

        return new ActionResult("profile");
    }
}
