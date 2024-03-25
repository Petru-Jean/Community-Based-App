package org.springprojects.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springprojects.exceptions.HandledException;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private JwtAuthenticationProvider jwtAuthProvider;

    @Autowired
    private UsernameAndPasswordAuthenticationProvider userPassAuthProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (jwtAuthProvider.supports(authentication.getClass())) {
            return jwtAuthProvider.authenticate(authentication);
        }

        if(userPassAuthProvider.supports(authentication.getClass())) {
            return userPassAuthProvider.authenticate(authentication);
        }

        throw new HandledException("Bad credentials!", HttpStatus.FORBIDDEN);
    }

}