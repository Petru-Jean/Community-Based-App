package org.springprojects.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springprojects.security.JwtAuthentication;
import org.springprojects.security.CustomAuthenticationManager;

import java.io.IOException;

@Component
public class JwtAuthFilter  extends OncePerRequestFilter
{
    @Autowired private CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String header = request.getHeader("Authorization");

        if (!StringUtils.hasText(header) || !header.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt =  header.substring(7);

        JwtAuthentication ca = new JwtAuthentication(jwt);

        var a = customAuthenticationManager.authenticate(ca);

        if(a.isAuthenticated())
        {
            System.out.println("UserDetails: " + ((UserDetails) ca.getPrincipal()).getUsername() );

            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request, response);

            return;
        }

        response.getWriter().write("Bad credentials!");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }


}
