package com.example.commentsapi.bean;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    User user;

    @Test
    public void user_User_Succes() {
        user = new User(1, "osman@gmail.com", "qweqwe", "jooz");
        assertEquals(user.getId(), 1);
        assertEquals(user.getEmail(), "osman@gmail.com");
        assertEquals(user.getPassword(), "qweqwe");
        assertEquals(user.getUsername(), "jooz");
    }
}
