package com.example.demo_spring_2.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception.getClass().isAssignableFrom(DisabledException.class)) {
            response.sendRedirect("/verifyError");
        }
        if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
            response.sendRedirect("/login?loginError=bad-credentials&email=" + request.getParameter("email") + "&password=" + request.getParameter("password"));
        }
        if (exception.getClass().isAssignableFrom(LockedException.class)) { //todo for students
            response.sendRedirect("loginError");
        }   if (exception.getClass().isAssignableFrom(LockedException.class)) { //todo for students
            response.sendRedirect("loginError");
        }
    }
}