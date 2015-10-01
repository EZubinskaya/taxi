package com.config.security.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kzub on 9/24/2015.
 */
@Component("ajaxLogoutSuccessHandler")
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null && authentication.getDetails() != null) {
            try {
                request.getSession().invalidate();
                System.out.println("User Successfully Logout");
            } catch (Exception e) {
                e.printStackTrace();
                e = null;
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);

        response.sendRedirect(request.getContextPath());
    }
}
