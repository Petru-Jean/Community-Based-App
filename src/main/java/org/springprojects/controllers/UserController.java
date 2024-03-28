package org.springprojects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springprojects.dto.userDTO.ViewUserDTO;
import org.springprojects.repositories.UserRepository;
import org.springprojects.services.UserService;

@RestController
@Validated
@RequestMapping("/api/v1/users")
public class UserController
{
    private final UserService userService;

    @Autowired
    public  UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("{username}")
    public ResponseEntity<EntityModel<ViewUserDTO>> getUser(@PathVariable String username)
    {
        return userService.loadByUsername(username);
    }

}
