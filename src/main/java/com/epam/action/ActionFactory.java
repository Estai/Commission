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
//        actions.put("POST/faculty", new ShowGroupAction());
//        actions.put("GET/faculty", new ShowPageAction("comission"));
        actions.put("POST/group", new ConfirmPageAction());
        actions.put("GET/confirm", new ShowPageAction("confirm"));
        actions.put("GET/group", new ShowPageAction("comission"));
        actions.put("POST/send", new ApplyAction());
        actions.put("GET/profile", new ProfileAction());
        actions.put("POST/changePass", new ChangePassAction());
//        actions.put("GET/changePass", new ShowPageAction("profile"));
        actions.put("POST/changeInfoEnrollee", new ChangeInfoEnrolleeAction());
        actions.put("POST/removeApplication", new DeleteApplicationAction());



        actions.put("GET/university", new ShowPageAction("university"));

      //  actions.put("GET/infoenr",new ShowEnrolleeInfoAction());
    }

    public Action getAction(String actionName) {
        return actions.get(actionName);
    }
}
