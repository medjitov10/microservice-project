package com.example.usersapi.model;

import java.util.List;

public class JwtResponse {

    private String jwt;
    private String username;

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }
    
    public JwtResponse(List<String> list) {
    	this.jwt = list.get(0);
    	this.username = list.get(1);
    }

    public String getToken() {
        return this.jwt;
    }
    
    public String getusername() {
    	return username;
    }
    
}