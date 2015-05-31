package com.epam.action;

import com.epam.manager.Helper;
import com.epam.entity.Enrollee;
import com.epam.file.excel.ExcelWritter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SaveExcel implements Action {
    ExcelWritter writter=new ExcelWritter();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Enrollee> enrollees = Helper.getInstance().getEnrolleeService().findAll();

//        writter.protect();
//        writter.readPassword();
        writter.update(enrollees);

        return new ShowEnrollee().execute(req,resp);
    }
}
