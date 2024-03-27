package org.springprojects.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springprojects.entities.User;

import java.util.Collection;

public class UserDetailsAuthentication extends AbstractAuthenticationToken {

    private UserDetails userDetails;


    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */

    public UserDetailsAuthentication(Collection<? extends GrantedAuthority> authorities)
    {
        super(authorities);
        this.userDetails = null;
    }

    @Override
    public Object getCredentials() {

        return null;
    }

    @Override
    public Object getPrincipal() {

        return userDetails;
    }

    public void setPrincipal(UserDetails userDetails)
    {
        this.userDetails = userDetails;
    }


}
