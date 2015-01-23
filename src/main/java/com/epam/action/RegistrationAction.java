package com.epam.action;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.dao.UserDao;
import com.epam.entity.Enrollee;
import com.epam.entity.Subject;
import com.epam.entity.User;
import com.epam.service.EnrolleeService;
import com.epam.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RegistrationAction implements Action {
    private ActionResult registrationAgain = new ActionResult("registration");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        final String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        boolean register = Validator.register(login, password, confirmPassword, req);

        if(!register){
            req.setAttribute("login",login);
            return registrationAgain;}

        UserService userService=new UserService();

        if (userService.findByLogin(login)) {
            req.setAttribute("login",login);
            req.setAttribute("loginError", "login is already taken");
            return registrationAgain;
        }
        final User user = new User();
        user.setLogin(login);
        user.setPassword(password);


        if (userService.create(user)) {
            req.getSession().setAttribute("user", user);
            req.setAttribute("success", "Thank you registered");
            return new ActionResult("index",true);
        }

        return registrationAgain;
    }

}
