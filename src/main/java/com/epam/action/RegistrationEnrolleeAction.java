package com.epam.action;

import com.epam.Helper;
import com.epam.dao.DaoCommand;
import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Enrollee;
import com.epam.entity.Faculty;
import com.epam.entity.Score;
import com.epam.entity.Subject;
import com.epam.service.EnrolleeService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.*;

public class RegistrationEnrolleeAction implements Action {
    ActionResult againPage=new ActionResult("infoenrolle");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String lastname = req.getParameter("lastname");
        String firstname = req.getParameter("firstname");
        String middlename = req.getParameter("middlename");
        String numberCertificate = req.getParameter("number");
        String certificate = req.getParameter("certificate");
        String idUser = req.getParameter("user.id");
        EnrolleeService enrolleeService = Helper.getInstance().getEnrolleeService();

        if ( Validator.isNullParameters(lastname, firstname, middlename, certificate, numberCertificate) || Validator.isNullParameters(req.getParameter("subj"))
                || Validator.isNullParameters(req.getParameter("score"))) {
            req.setAttribute("dataError", "Не все данные введены");
            return againPage;
        }

        Iterator<Subject> iterator = Helper.getInstance().getSubjectService().findMainSubject().iterator();
        Map<Integer,Integer> scoreMap=new HashMap<>();
        while(iterator.hasNext()){
            Subject subject = iterator.next();
            String score = req.getParameter("score[" + subject.getId() + "]");
            if(Validator.isNullParameters(score)){
                req.setAttribute("dataError", "Не все данные введены");
                return againPage;
            }
          scoreMap.put(Integer.parseInt(req.getParameter(subject.getName())),Integer.parseInt(score));
        }
        scoreMap.put(Integer.parseInt(req.getParameter("subj")),Integer.parseInt(req.getParameter("score")));



        Enrollee enrollee = new Enrollee();

        enrollee.setIdUser(Integer.parseInt(idUser));
        enrollee.setLastName(lastname);
        enrollee.setFirstName(firstname);
        enrollee.setMiddleName(middlename);
        enrollee.setCertificate(Double.parseDouble(certificate));
        enrollee.setCertificateNumber(numberCertificate);

        Enrollee enrol = null;
        try {
            enrol = enrolleeService.create(enrollee, scoreMap);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        req.getSession().setAttribute("enrollee", enrol);
        return new ActionResult("comission");
    }
}
