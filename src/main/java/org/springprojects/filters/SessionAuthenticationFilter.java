package org.springprojects.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springprojects.security.UserDetailsAuthentication;
import org.springprojects.services.UserService;

import java.io.IOException;
import java.util.List;

@Component
public class SessionAuthenticationFilter extends OncePerRequestFilter
{
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        var session = request.getSession(false);

        SecurityContextHolder.clearContext();

        if(session != null && session.getAttribute("User") != null)
        {
            var uda = new UserDetailsAuthentication(List.of());

            uda.setAuthenticated(true);
            uda.setPrincipal(userService.loadUserByUsername(session.getAttribute("User").toString()));

            SecurityContextHolder.getContext().setAuthentication(uda);
        }

        filterChain.doFilter(request, response);
    }


}
