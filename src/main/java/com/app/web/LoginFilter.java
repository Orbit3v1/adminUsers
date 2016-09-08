package com.app.web;

import com.app.data.entity.Person;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ayaroslavtsev on 29.04.2016.
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();

        if (!path.startsWith("/screen/LoginScreen.xhtml")) {
            String loginUrl = request.getContextPath() + "/screen/LoginScreen.xhtml";
            HttpSession session = request.getSession();
            Person user = (session != null) ? (Person) session.getAttribute("user") : null;
            if (user == null) {
                response.sendRedirect(loginUrl);
            } else {
                MDC.put("login", user.getLogin());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
