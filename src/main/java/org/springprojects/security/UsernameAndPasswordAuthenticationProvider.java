package org.springprojects.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springprojects.services.UserService;

import java.util.Collection;
import java.util.Objects;

@Component
public class UsernameAndPasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userService;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        UsernameAndPasswordAuthentication upa = (UsernameAndPasswordAuthentication) authentication;

        UserDetails userDetails = userService.loadUserByUsername(upa.getUsername());

        if (passwordEncoder.matches(userDetails.getPassword(), upa.getPassword())) {

            upa.setAuthenticated(true);
            upa.setPrincipal(userDetails);

        }

        return upa;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernameAndPasswordAuthenticationProvider.class.equals(authentication);
    }

}
