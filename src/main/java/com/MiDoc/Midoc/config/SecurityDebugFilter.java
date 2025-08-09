package com.MiDoc.Midoc.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityDebugFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException {

        System.out.println("🔍 Incoming request: " + request.getMethod() + " " + request.getRequestURI());
        System.out.println("🔐 Auth type: " + request.getAuthType());
        System.out.println("🧠 Remote user: " + request.getRemoteUser());
        System.out.println("🍪 Cookies: " + (request.getCookies() != null ? request.getCookies().length : 0));

        filterChain.doFilter(request, response);
    }
}