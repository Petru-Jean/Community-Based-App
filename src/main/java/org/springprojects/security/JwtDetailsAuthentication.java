package org.springprojects.security;

import org.springframework.security.core.authority.AuthorityUtils;

public class JwtDetailsAuthentication extends UserDetailsAuthentication
{
    private final String      jwt;

    public JwtDetailsAuthentication(String jwt)
    {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.jwt  =  jwt;
    }

    public String getJwt()
    {
        return jwt;
    }

}
