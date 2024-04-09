package org.springprojects.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springprojects.dto.userDTO.LoginUserDTO;
import org.springprojects.dto.userDTO.RegisterUserDTO;
import org.springprojects.dto.userDTO.UserMapper;
import org.springprojects.dto.userDTO.ViewUserDTO;
import org.springprojects.entities.User;
import org.springprojects.exceptions.HandledException;
import org.springprojects.repositories.UserRepository;

import java.io.IOException;

@Service
public class AuthenticationService
{
    @Autowired private UserRepository repository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public ResponseEntity<ViewUserDTO> registerUser(RegisterUserDTO dto)
    {

        if(repository.findByUsername(dto.getUsername()) != null)
        {
            throw new HandledException("Username " + dto.getUsername() + " already in use", HttpStatus.UNAUTHORIZED);
        }

        if(repository.findByEmail(dto.getEmail()) != null)
        {
            throw new HandledException("Email " + dto.getEmail() + " already in use.", HttpStatus.UNAUTHORIZED);
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        User user = repository.save (UserMapper.INSTANCE.map(dto));

        ViewUserDTO viewUserDTO = UserMapper.INSTANCE.map(user);

        createSession(dto.getUsername());

        return new ResponseEntity<>(viewUserDTO, HttpStatus.CREATED);
    }



    public ResponseEntity<ViewUserDTO> loginUser(LoginUserDTO dto)
    {
        User dbUser = repository.findByUsername(dto.getUsername());

        if(dbUser == null || !passwordEncoder.matches(dto.getPassword(), dbUser.getPassword()))
        {
            throw new HandledException("The provided credentials are not valid.", HttpStatus.UNAUTHORIZED);
        }

        ViewUserDTO viewUserDTO = UserMapper.INSTANCE.map(dbUser);

        createSession(dto.getUsername());


        return new ResponseEntity<>(viewUserDTO, HttpStatus.OK);
    }

    private void createSession(String username)
    {
        var session = request.getSession(true);
        session.setAttribute("User", username);
    }

}
