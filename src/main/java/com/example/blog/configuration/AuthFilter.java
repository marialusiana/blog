package com.example.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Auth Filter 
 */
public class AuthFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String imei = request.getParameter("imei");
        String username = request.getParameter("username");
        String passw = request.getParameter("password");

        String usernameDomain = String.format("%s%s%s", username.trim(),
                String.valueOf(Character.LINE_SEPARATOR), imei);

        System.out.println(usernameDomain + " FILTER");

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(usernameDomain, passw);
        // authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
