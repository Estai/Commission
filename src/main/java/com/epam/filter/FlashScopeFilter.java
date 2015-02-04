package com.epam.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class FlashScopeFilter implements Filter {
    private static final String FLASH_SESSION_KEY = "FLASH_SESSION_KEY";
    private static final String FLASH_PARAMETER_PREFIX = "flash.";


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        doFilter((HttpServletRequest) req, (HttpServletResponse) resp, chain);
    }

    private void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
            if (session != null) {
                Map<String, Object> flashParams = (Map<String, Object>) session.getAttribute(FLASH_SESSION_KEY);
                if (flashParams != null) {
                    for (Map.Entry<String, Object> flashEntry : flashParams.entrySet()) {
                        req.setAttribute(flashEntry.getKey(), flashEntry.getValue());
                    }
                    session.removeAttribute(FLASH_SESSION_KEY);
                }
            }

        chain.doFilter(req, resp);
        Map<String, Object> flashParams = new HashMap<String, Object>();
            Enumeration<?> e = req.getAttributeNames();
            while (e.hasMoreElements()) {
                String paramName = (String) e.nextElement();
                if (paramName.startsWith(FLASH_PARAMETER_PREFIX)) {
                    Object value = req.getAttribute(paramName);
                    paramName = paramName.substring(FLASH_PARAMETER_PREFIX.length());
                    flashParams.put(paramName, value);
                }
            }
            if (!flashParams.isEmpty()) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute(FLASH_SESSION_KEY, flashParams);
            }
        }

    public void init(FilterConfig filterConfig) throws ServletException {
        // no-op
    }

    public void destroy() {
        // no-op
    }
}
