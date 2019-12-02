package com.example.apigateway.config;

import com.netflix.zuul.context.RequestContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class AuthenticationFilterTest {
    AuthenticationFilter authenticationFilter;
    @Mock
    RequestContext requestContext;

    @Mock
    SecurityContextHolder securityContextHolder;

    @Before
    public void init() {
        authenticationFilter = new AuthenticationFilter();
    }

    @Test
    public void authenticationFilterTest_Success() {
        assertEquals(authenticationFilter.filterType(), "pre");
        assertEquals(authenticationFilter.filterOrder(), 1);
        assertEquals(authenticationFilter.shouldFilter(), true);
    }

}
