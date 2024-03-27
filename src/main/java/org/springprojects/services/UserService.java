package org.springprojects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springprojects.entities.User;
import org.springprojects.exceptions.NotFoundException;
import org.springprojects.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        var user = userRepository.findByUsername(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("Username '" + username + "' not found.");

        return user.get();
    }

/*    public User loadByUserDetails(UserDetails userDetails)
    {
        if(userDetails == null)
        {
            throw new NotFoundException("The provided user does not exist");
        }

        var user = userRepository.findByUsername(userDetails.getUsername());

        if(user.isEmpty())
        {
            throw new NotFoundException("The provided user does not exist");
        }

        return user.get();
    }*/

}