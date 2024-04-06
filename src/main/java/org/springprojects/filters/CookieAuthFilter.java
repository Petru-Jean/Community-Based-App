package org.springprojects.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CookieAuthFilter extends OncePerRequestFilter
{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        System.out.println("? request");
        var cookies = request.getCookies();

        for(var cookie : cookies)
        {
            if(cookie.getName().equalsIgnoreCase("Auth"))
            {
                var value =  cookie.getValue();

                //System.out.println(value);
                

            }
        }

        filterChain.doFilter(request, response);



    }
}
