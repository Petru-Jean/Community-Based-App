package org.springprojects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springprojects.entities.User;
import org.springprojects.services.JwtService;
import org.springprojects.services.UserService;

import java.util.Objects;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController
{
   @Autowired
   private JwtService jwtService;

    @GetMapping("/ViewToken/{token}")
    public String viewJwtToken(@PathVariable String token) {
        try {
            return jwtService.auth(token).getUsername();
        } catch (Exception ex) {
            return "Invalid JWToken:\n" + ex.getMessage();
        }
    }

    @PostMapping("/CreateToken")
    public String createJwtToken() {
        var user = new User();

        user.setId(2);
        user.setUsername("Mihai");

        return jwtService.generateToken(user);
    }


}
