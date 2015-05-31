package com.epam.manager;


import com.epam.service.*;

public class Helper {
    private static final Helper INSTANCE=new Helper();
    private SubjectService subjectService=new SubjectService();
    private ScoreService scoreService=new ScoreService();
    private FacultyService facultyService=new FacultyService();
    private GroupService groupService=new GroupService();
    private ApplicationService applicationService=new ApplicationService();
    private EnrolleeService enrolleeService=new EnrolleeService();
    private UserService userService=new UserService();

    private Helper() {
    }
    public static Helper getInstance(){
        return INSTANCE;
    }

    public SubjectService getSubjectService() {
        return subjectService;
    }

    public ScoreService getScoreService() {
        return scoreService;
    }

    public FacultyService getFacultyService() {
        return facultyService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public ApplicationService getApplicationService() {
        return applicationService;
    }

    public EnrolleeService getEnrolleeService() {
        return enrolleeService;
    }

    public UserService getUserService() {
        return userService;
    }
}
