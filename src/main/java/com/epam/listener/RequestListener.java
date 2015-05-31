package com.epam.listener;

import com.epam.manager.LocaleManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.util.Locale;

public class RequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        LocaleManager localeManager=LocaleManager.getInstance();
        ServletRequest request = servletRequestEvent.getServletRequest();
        String lang = request.getParameter("lang");
        Locale locale = localeManager.setLocale(lang);
        servletRequestEvent.getServletContext().setAttribute("locale",locale);
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
