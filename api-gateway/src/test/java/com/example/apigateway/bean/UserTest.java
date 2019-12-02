package com.example.apigateway.bean;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {
    UserBean user;

    @Test
    public void user_User_Succes() {
        user = new UserBean(1L, "osman@gmail.com","jooz", "qweqwe");
        assertNotNull(user.getId());
        assertEquals(user.getEmail(), "osman@gmail.com");
        assertEquals(user.getPassword(), "qweqwe");
        assertEquals(user.getUsername(), "jooz");
    }
}
