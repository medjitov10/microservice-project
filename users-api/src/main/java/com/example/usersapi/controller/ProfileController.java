package com.example.usersapi.controller;

import com.example.usersapi.model.Profile;
import com.example.usersapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Profile getProfile(@RequestHeader("Authorization") String tokenHeader) {
        return userService.getProfile(tokenHeader);
    }

    @PostMapping
    public Profile createProfile(@RequestHeader("Authorization") String tokenHeader, @RequestBody Profile profile) {
        return userService.createProfile(profile, tokenHeader);
    }

}

