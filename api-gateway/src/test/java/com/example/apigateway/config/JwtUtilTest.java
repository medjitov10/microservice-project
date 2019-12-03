package com.example.apigateway.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JwtUtilTest {
    private String jwtToken;
    private Date date;
    private String username;
    @InjectMocks
    JwtUtil jwtUtil;

    @Mock
    UserDetails userDetails;
    @Before
    public void init() {
        jwtUtil.setSecret("pancakes");
        when(userDetails.getUsername()).thenReturn("ben");
        jwtToken = jwtUtil.generateToken(userDetails.getUsername());
        date = new Date();
        username = "username";
    }
    @Test
    public void getUserNameFromToken_String_Success(){
        String token = jwtUtil.getUsernameFromToken(jwtToken);
        assertNotNull(token);
    }
    @Test
    public void validateToken_Boolean_Success(){
        Boolean isTrue = jwtUtil.validateToken(jwtToken, userDetails);
        assertNotNull(isTrue);
    }
    @Test
    public void istokenExpired_Boolean_Success(){
        jwtUtil.getExpirationDateFromToken(jwtToken);
        Boolean isExpired = jwtUtil.isTokenExpired(jwtToken);
        assertNotNull(isExpired);
    }
}
