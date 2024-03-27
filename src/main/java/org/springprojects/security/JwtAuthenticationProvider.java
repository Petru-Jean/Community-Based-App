package org.springprojects.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springprojects.services.JwtService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private JwtService jwtService;

    @Value("${api.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        JwtDetailsAuthentication ca = (JwtDetailsAuthentication) authentication;

        try {

            ca.setPrincipal(jwtService.auth( ca.getJwt() ));
            ca.setAuthenticated(true);

        }catch(Exception ignore) {}

        return ca;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtDetailsAuthentication.class.equals(authentication);
    }

}