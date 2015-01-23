package com.epam.action;

/**
 * Created by User on 16.12.14.
 */
public class ActionResult {
    private String view;
    private boolean redirect;

    public ActionResult(String view, boolean redirect) {
        this.view = view;
        this.redirect = redirect;
    }

    public ActionResult(String view) {
        this.view = view;
        this.redirect = false;
    }

    public String getView() {
        return view;
    }

    public boolean isRedirect() {
        return redirect;
    }
}
