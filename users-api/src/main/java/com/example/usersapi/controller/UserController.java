package com.example.usersapi.controller;


import com.example.usersapi.model.JwtResponse;
import com.example.usersapi.model.User;
import com.example.usersapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;

/**
 * @RestCOntroller annotation is used to create a controller mapping to this class
 */

@RestController
public class UserController {

    /**
     * inject UserService bean through dependency injection
     */
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Users list", notes = "user makes a request to view all users", response = Iterable.class)
    @GetMapping("/list")
    public Iterable<User> listUsers(@RequestHeader("username") String username) {
        return userService.listUsers();
    }

    @ApiOperation(value = "signup", notes = "User provides email, username and password to signup", response = ResponseEntity.class)
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(new JwtResponse(userService.signUp(user)));
    }

    @ApiOperation(value = "login", notes = "User provides email and password to login", response = ResponseEntity.class)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws CredentialException {
        return ResponseEntity.ok(new JwtResponse(userService.logIn(user)));
    }
}
