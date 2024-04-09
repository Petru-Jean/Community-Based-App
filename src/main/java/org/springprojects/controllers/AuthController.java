package org.springprojects.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springprojects.dto.userDTO.LoginUserDTO;
import org.springprojects.dto.userDTO.RegisterUserDTO;
import org.springprojects.dto.userDTO.ViewUserDTO;
import org.springprojects.services.AuthenticationService;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController
{
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<ViewUserDTO> login(@RequestBody @Valid LoginUserDTO dto)
    {
        return authService.loginUser(dto);
    }

    @PostMapping("/register")
    public ResponseEntity<ViewUserDTO> register(@RequestBody @Valid RegisterUserDTO dto)
    {
        return authService.registerUser(dto);
    }


}
