package com.config.security.handlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kzub on 9/24/2015.
 */
@Component("ajaxAuthenticationSuccessHandler")
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//    private AuthenticationSuccessHandler defaultHandler;
//
//    public AjaxAuthenticationSuccessHandler() {
//
//    }
//    public AjaxAuthenticationSuccessHandler(AuthenticationSuccessHandler defaultHandler) {
//        this.defaultHandler = defaultHandler;
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {

        if (Boolean.TRUE.equals(request.getHeader("X-Ajax-call"))) {
            response.getWriter().print("Authenticate Successful");
            response.getWriter().print("ok");
            response.getWriter().flush();
        } else {
            SavedRequest savedRequest =
                    new HttpSessionRequestCache().getRequest(request, response);
            if (savedRequest == null) {
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                response.sendRedirect(savedRequest.getRedirectUrl());
            }
        }
    }
}
