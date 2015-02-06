package com.epam.action;

import com.epam.Helper;
import com.epam.entity.Faculty;
import com.epam.entity.Group;
import com.epam.entity.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddFaculty implements Action {
    Action addPage=new ShowPageAction("admin/addPage");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String decan = req.getParameter("decan");
        String[] subjs = req.getParameterValues("subj");
        String[] groups = req.getParameterValues("group");
        String[] numbers = req.getParameterValues("number");
        if(Validator.isNullParameters(name) || Validator.isNullParameters(decan)){
            req.setAttribute("Parameter","Проверьте введенные данные");
            return addPage.execute(req,resp);
        }
        Faculty entity=new Faculty();
        entity.setName(name);
        entity.setDecan(decan);
        boolean findFaculty = Helper.getInstance().getFacultyService().findByName(entity.getName());
        if(findFaculty)
        {
            req.setAttribute("Parameter","Такой факультет уже существует");
            return addPage.execute(req,resp);
        }
        Faculty faculty = Helper.getInstance().getFacultyService().create(entity);
   if(subjs!=null){
        for (int i = 0; i < subjs.length; i++) {
            if(Validator.isNullParameters(groups[i]) || Validator.isNullParameters(numbers[i]))
            {req.setAttribute("Parameter","Проверьте введенные данные");
                return addPage.execute(req,resp);}
            Group newGroup=new Group();
            Subject subject=new Subject();
            subject.setId(Integer.parseInt(subjs[i]));
            newGroup.setName(groups[i]);
            newGroup.setProfileSubject(subject);
            newGroup.setNumberStudent(Integer.parseInt(numbers[i]));
            newGroup.setIdFaculty(faculty.getId());
            Group group = Helper.getInstance().getGroupService().create(newGroup);
            faculty.add(group);
        }}
        List<Faculty> faculties = (List<Faculty>) req.getServletContext().getAttribute("faculties");
        faculties.add(faculty);

        return addPage.execute(req,resp);
    }
}
