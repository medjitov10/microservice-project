package com.example.postapi.exception;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EntityNotFoundTest {
    EntityNotFoundException entityNotFoundException;

    @Before
    public void init() {
        entityNotFoundException = new EntityNotFoundException("msg");
    }

    @Test
    public void EntityNotFoundExceptionController_Success() {
        assertEquals(entityNotFoundException.getMessage(), "msg");
    }
}
