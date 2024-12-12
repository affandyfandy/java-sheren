package com.example.assignment1.filter;

import jakarta.servlet.ServletException;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomOncePerRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {
        System.out.println("CustomOncePerRequestFilter is executed");

        try {
            filterChain.doFilter(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}