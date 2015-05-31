package com.epam.action;

import com.epam.entity.Gender;
import com.epam.entity.Language;
import com.epam.manager.DateManager;
import com.epam.manager.Helper;
import com.epam.dao.DaoException;
import com.epam.entity.Enrollee;
import com.epam.entity.Subject;
import com.epam.service.EnrolleeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RegistrationEnrolleeAction implements Action {
    ActionResult againPage=new ActionResult("infoenrolle");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String lastname = req.getParameter("lastname");
        String firstname = req.getParameter("firstname");
        String middlename = req.getParameter("middlename");
        String numberCertificate = req.getParameter("seria")+" "+req.getParameter("number");
        String iin = req.getParameter("iin");
        Date birthday = null;
        String gender = req.getParameter("gender");
        String nationality = req.getParameter("nationality");
        String contactNumber = req.getParameter("contactNumber");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String educationalInstitution=req.getParameter("educationalInstitution")+"/"+req.getParameter("region");
        String ownerGoldMedal = req.getParameter("ownerGoldMedal");
        String excellentPupil = req.getParameter("excellentPupil");
        String formStudy = req.getParameter("formStudy");
        String language = req.getParameter("language");


        String idUser = req.getParameter("user.id");

        EnrolleeService enrolleeService = Helper.getInstance().getEnrolleeService();

        if ( Validator.isNullParameters(lastname, firstname, middlename, iin, numberCertificate) || Validator.isNullParameters(req.getParameter("subj"))
                || Validator.isNullParameters(req.getParameter("score"))|| Validator.isNullParameters(address, req.getParameter("year"), req.getParameter("month"), req.getParameter("day"), gender) ||
                Validator.isNullParameters(nationality, contactNumber, email,educationalInstitution , ownerGoldMedal)||
                Validator.isNullParameters(nationality, contactNumber, excellentPupil,formStudy , language) ) {

            req.setAttribute("dataError", "Не все данные введены");
            return againPage;
        }

            birthday=DateManager.installDate(req.getParameter("year"), req.getParameter("month"), req.getParameter("day"));


        Iterator<Subject> iterator = Helper.getInstance().getSubjectService().findMainSubject().iterator();
        Map<Integer,Integer> scoreMap=new HashMap<>();
        while(iterator.hasNext()){
            Subject subject = iterator.next();
            String score = req.getParameter("score[" + subject.getId() + "]");
            if(Validator.isNullParameters(score)){
                req.setAttribute("dataError", "Не все данные введены");
                return againPage;
            }
          scoreMap.put(Integer.parseInt(String.valueOf(subject.getId())),Integer.parseInt(score));
        }
        scoreMap.put(Integer.parseInt(req.getParameter("subj")),Integer.parseInt(req.getParameter("score")));



        Enrollee enrollee = new Enrollee();

        enrollee.setIdUser(Integer.parseInt(idUser));
        enrollee.setLastName(lastname);
        enrollee.setFirstName(firstname);
        enrollee.setMiddleName(middlename);
        enrollee.setBirthday(birthday);
        enrollee.setIIN(Long.parseLong(iin));
        enrollee.setBirthday(birthday);
        enrollee.setCertificateNumber(numberCertificate);
        enrollee.setGender(Gender.valueOf(gender.toUpperCase()));
        enrollee.setNationality(nationality);
        enrollee.setMobileNumber(contactNumber);
        enrollee.setEmail(email);
        enrollee.setAddress(address);
        enrollee.setEducationalInstitution(educationalInstitution);
        enrollee.setGoldMedal(Boolean.parseBoolean(ownerGoldMedal));
        enrollee.setExcellentPupil(Boolean.parseBoolean(excellentPupil));
        enrollee.setFormStudy(formStudy);
        enrollee.setLanguage(Language.valueOf(language.toUpperCase()));

        Enrollee enrol = null;
        try {
            enrol = enrolleeService.create(enrollee, scoreMap);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        req.getSession().setAttribute("enrollee", enrol);
        return new ActionResult("upload",true);
    }
}
