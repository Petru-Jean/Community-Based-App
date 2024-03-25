package org.springprojects.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtAuthentication extends UserDetailsAuthentication
{
    private final String      jwt;

    public JwtAuthentication(String jwt)
    {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.jwt  =  jwt;
    }

    public String getJwt()
    {
        return jwt;
    }

}
