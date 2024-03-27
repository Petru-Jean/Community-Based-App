package org.springprojects.security;

import org.springframework.security.core.authority.AuthorityUtils;

public class UsernameAndPasswordDetailsAuthentication extends UserDetailsAuthentication
{

    private final String username, password;

    public UsernameAndPasswordDetailsAuthentication(String username, String password)
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
