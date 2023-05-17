package com.example.demo_spring_2.handler;

import com.example.demo_spring_2.security.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * Access deniedHandler (403)
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        if (currentUser.getUser().isAdmin()) {
            response.sendRedirect("/admin/home?errorType=accessDenied");
        } else {
            response.sendRedirect("/user/home?errorType=accessDenied");
        }
    }
}