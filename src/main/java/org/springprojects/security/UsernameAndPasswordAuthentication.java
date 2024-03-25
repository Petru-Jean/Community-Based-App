package org.springprojects.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class UsernameAndPasswordAuthentication extends UserDetailsAuthentication {

    private final String username, password;

    public UsernameAndPasswordAuthentication(String username, String password)
    {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }


}
