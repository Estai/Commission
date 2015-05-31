package com.epam.action;

import com.epam.manager.Helper;
import com.epam.entity.Enrollee;
import com.epam.entity.Score;
import com.epam.entity.Subject;
import com.epam.service.EnrolleeService;
import com.epam.service.ScoreService;
import com.epam.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ChangeInfoEnrolleeAction implements Action {
    EnrolleeService enrolleeService = Helper.getInstance().getEnrolleeService();
    ScoreService scoreService=Helper.getInstance().getScoreService();
    SubjectService subjectService=Helper.getInstance().getSubjectService();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Enrollee enrollee=(Enrollee)req.getSession().getAttribute("enrollee");
        String lastname = req.getParameter("lastname");
        String firstname = req.getParameter("firstname");
        String middlename = req.getParameter("middlename");
        String numberCertificate = req.getParameter("number");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String mobileNumber = req.getParameter("mobileNumber");

        Iterator<Subject> iterator = Helper.getInstance().getSubjectService().findMainSubject().iterator();
        Map<Subject,Score> parameter=new HashMap<>();
        while(iterator.hasNext()){
            Subject subject = iterator.next();
            String score = req.getParameter("scoreID[" + subject.getId() + "]");
            parameter.put(subjectService.findById(subject.getId()), scoreService.findById(Integer.parseInt(score)));

        }

        parameter.put(subjectService.findById(Integer.parseInt(req.getParameter("subj"))), scoreService.findById(Integer.parseInt(req.getParameter("score[]"))));
     if(Validator.isChange(scoreService.findById(Integer.parseInt(req.getParameter("score[]"))),enrollee.getScore())) {
         req.getSession().removeAttribute("applications");

     }
        enrolleeService.updateScore(parameter, enrollee.getId());
        enrollee.setFirstName(firstname);
        enrollee.setLastName(lastname);
        enrollee.setMiddleName(middlename);
        enrollee.setCertificateNumber(numberCertificate);
        enrollee.setEmail(email);
        enrollee.setAddress(address);
        enrollee.setMobileNumber(mobileNumber);
//        enrollee.setCertificate(Double.parseDouble(certificate));
        enrollee.setScore(parameter);
        req.getSession().setAttribute("subjectsMap",parameter);
        enrolleeService.update(enrollee);
        return new ActionResult("profile");
    }
}
