package com.epam.action;

import com.epam.dao.DaoCommand;
import com.epam.dao.DaoException;
import com.epam.dao.DaoFactory;
import com.epam.dao.DaoManager;
import com.epam.entity.Enrollee;
import com.epam.entity.Faculty;
import com.epam.entity.Score;
import com.epam.entity.Subject;
import com.epam.service.EnrolleeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationEnrolleeAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String lastname = req.getParameter("lastname");
        String firstname = req.getParameter("firstname");
        String middlename = req.getParameter("middlename");
        String numberCertificate = req.getParameter("number");
        String certificate = req.getParameter("certificate");
        String idUser = req.getParameter("user.id");
        EnrolleeService enrolleeService = new EnrolleeService();
        Map<Integer, Integer> scoreMap = new HashMap<>();
        boolean nullParameters = Validator.isNullParameters(req.getParameter("subj[1]"), req.getParameter("subj[2]"), req.getParameter("subj[3]"), req.getParameter("subj[4]"), req.getParameter("subj[5]"));
        if(nullParameters){
            req.setAttribute("dataError", "Error");
            return new ActionResult("infoenrolle");
        }
        scoreMap.put(Integer.parseInt(req.getParameter("subj[1]")), Integer.parseInt(req.getParameter("score[1]")));
        scoreMap.put(Integer.parseInt(req.getParameter("subj[2]")), Integer.parseInt(req.getParameter("score[2]")));
        scoreMap.put(Integer.parseInt(req.getParameter("subj[3]")), Integer.parseInt(req.getParameter("score[3]")));
        scoreMap.put(Integer.parseInt(req.getParameter("subj[4]")), Integer.parseInt(req.getParameter("score[4]")));
        scoreMap.put(Integer.parseInt(req.getParameter("subj[5]")), Integer.parseInt(req.getParameter("score[5]")));

        List<Integer> lst = (List<Integer>) req.getSession().getAttribute("lst");
        boolean registerEnrollee = Validator.registerEnrollee(lastname, firstname, middlename, certificate, numberCertificate, scoreMap, lst);
        if (!registerEnrollee) {
            req.setAttribute("dataError", "Error");
            return new ActionResult("infoenrolle");
        }

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
        List<Faculty> faculties = (List<Faculty>) DaoFactory.getDaoFactory("jdbc").createDaoManager().executeAndClose(new DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) {
                return daoManager.getFacultyDao().findAll();
            }
        });
        req.getSession().setAttribute("faculties", faculties);
        return new ActionResult("comission");
    }
}
