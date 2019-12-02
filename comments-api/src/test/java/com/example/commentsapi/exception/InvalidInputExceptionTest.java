package com.example.commentsapi.exception;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidInputExceptionTest {
    InvalidInputException invalidInputException;

    @Before
    public void init() {
        invalidInputException = new InvalidInputException("msg");
    }

    @Test
    public void EntityNotFoundExceptionController_Success() {
        assertEquals(invalidInputException.getMessage(), "msg");
    }
}
