package com.example.usersapi.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class JwtUtilTest {
    private String secret = "pancakes";
    private String username = "test";

    @InjectMocks
    JwtUtil jwtUtil;

    @Before
    public void init() {
        jwtUtil.setSecret(secret);
    }



    @Test
    public void generateToken_String_SUCCESS() {
        String token = jwtUtil.generateToken(username);
        assertNotNull(token);
    }
}
