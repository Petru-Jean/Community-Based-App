package org.springprojects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springprojects.controllers.UserController;
import org.springprojects.dto.userDTO.UserMapper;
import org.springprojects.dto.userDTO.ViewUserDTO;
import org.springprojects.entities.User;
import org.springprojects.exceptions.NotFoundException;
import org.springprojects.repositories.UserRepository;

import javax.sound.midi.SysexMessage;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        var user = userRepository.findByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("Username '" + username + "' not found.");

        return user;
    }

    public ResponseEntity<EntityModel<ViewUserDTO>> loadByUsername(String username)
    {
        User user = userRepository.findByUsername(username);

        if(user == null)
        {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }

        ViewUserDTO userDTO = UserMapper.INSTANCE.map(user);

        return new ResponseEntity<>(EntityModel.of(userDTO, linkTo(UserController.class).slash("").slash(userDTO.getUsername()).withSelfRel()), HttpStatus.OK);
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