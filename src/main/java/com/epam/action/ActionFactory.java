package com.epam.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();
        actions.put("GET/", new ShowPageAction("index"));
        actions.put("GET/index",new ShowPageAction("index"));
        actions.put("GET/registration", new ShowPageAction("registration"));
        actions.put("POST/registration", new RegistrationAction());
        actions.put("GET/login", new ShowPageAction("registration"));
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/infoenrollee", new ShowPageRegistrationEnrollee());
        actions.put("POST/comission",new RegistrationEnrolleeAction());
        actions.put("GET/comission", new ShowPageAction("comission"));

//        actions.put("GET/faculty", new ShowPageAction("comission"));
        actions.put("POST/group", new ConfirmPageAction());
        actions.put("GET/confirm", new ShowPageAction("confirm"));
        actions.put("GET/group", new ShowPageAction("comission"));
        actions.put("POST/send", new ApplyAction());
        actions.put("GET/send", new ShowPageAction("comission"));
        actions.put("GET/profile", new ProfileAction());
        actions.put("POST/changePass", new ChangePassAction());
//        actions.put("GET/changePass", new ShowPageAction("profile"));
        actions.put("POST/changeInfoEnrollee", new ChangeInfoEnrolleeAction());
        actions.put("POST/removeApplication", new DeleteApplicationAction());
        actions.put("GET/admin",new AdminAction());
        actions.put("GET/admin/faculty", new ShowPageAction("admin/faculty"));
        actions.put("POST/deleteFaculty",new DeleteFaculty());
        actions.put("GET/edit",new EditGroup());
        actions.put("GET/getPriority",new GetPriority());
        actions.put("GET/updatePage", new ShowPageAction("updatePage"));
        actions.put("POST/updatePriority",new UpdatePriority());
        actions.put("GET/addPage",new ShowPageAction("admin/addPage"));
        actions.put("POST/addFaculty",new AddFaculty());
        actions.put("GET/admin/enrollee",new ShowEnrollee());
        actions.put("GET/admin/saveExcel",new SaveExcel());
        actions.put("GET/upload",new ShowPageAction("upload"));
        actions.put("POST/upload",new UploadAction());
        actions.put("POST/mail", new MessageAction());
        actions.put("GET/university", new ShowPageAction("university"));
        actions.put("GET/news", new ShowPageAction("news"));
        actions.put("GET/admin/document", new ShowPageAction("admin/document"));

      //  actions.put("GET/infoenr",new ShowEnrolleeInfoAction());
    }

    public Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
