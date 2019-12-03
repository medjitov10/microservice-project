package com.example.usersapi.controller;

import com.example.usersapi.exception.EntityNotFoundException;
import com.example.usersapi.model.Profile;
import com.example.usersapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @RestCOntroller and RequestMapping annotations are used to create a mapping to this class and to every API endpoint
 */

@RestController
@RequestMapping ("/profile")
public class ProfileController {

    /**
     * inject UserService bean through dependency injection
     */
    @Autowired
    private UserService userService;

    @ApiOperation(value = "get profile", notes = "authorized user gets access to own profile ", response = Profile.class)
    @GetMapping
    public Profile getProfile(@RequestHeader("username") String username) {
        return userService.getProfile(username);
    }

    @ApiOperation(value = "create profile", notes = "User provides additional email, mobile number and address to create profile", response = Profile.class)
    @PostMapping
    public Profile createProfile(@RequestHeader("username") String username, @RequestBody Profile profile) throws EntityNotFoundException {
        return userService.createProfile(profile, username);
    }

}

