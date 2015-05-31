package com.epam.filter;

import com.epam.entity.Role;
import com.epam.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, chain);
    }

    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String pathInfo = req.getPathInfo();

        if (pathInfo.endsWith("infoenrollee")) {
            if (req.getSession().getAttribute("enrollee") != null ) {
                String location = req.getContextPath() + "/do/comission";
                resp.sendRedirect(location);
                return;
//                chain.doFilter(req, resp);
            }

        }
        if(pathInfo.endsWith("registration") || pathInfo.endsWith("login")){
            if (req.getSession().getAttribute("user") != null) {
                String location = req.getContextPath() + "/do/profile";
                resp.sendRedirect(location);
                return;}
        }
        if( pathInfo.endsWith("group")){
            if (req.getSession().getAttribute("enrollee") == null) {
                String location = req.getContextPath() + "/do/infoenrollee";
                resp.sendRedirect(location);
                return;}
        }
        if(pathInfo.endsWith("profile")){
            if (req.getSession().getAttribute("user") == null) {
                String location = req.getContextPath() + "/do/login";
                resp.sendRedirect(location);
                return;}
        }
        if(pathInfo.startsWith("/admin")){
            User user = (User) req.getSession().getAttribute("user");
            if (user != null) {

                if(user.getRole()!=Role.ADMIN) {

                    resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
//                    String location = req.getContextPath() + "/do/login";
//                    resp.sendRedirect(location);
//                    return;
                }}
            else{ resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;}
        }
//        if(pathInfo.endsWith("/send")){
//            String location = req.getContextPath() + "/do/comission";
//            resp.sendRedirect(location);
//            return;
//        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
