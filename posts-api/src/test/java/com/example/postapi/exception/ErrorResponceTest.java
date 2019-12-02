package com.example.postapi.exception;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class ErrorResponceTest {
    ErrorResponse errorResponse;
    ErrorResponse errorResponse2;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Before
    public void init() {
        errorResponse = new ErrorResponse(HttpStatus.OK, "message", "cause");
        errorResponse2 = new ErrorResponse(HttpStatus.OK, "message");
    }

    @Test
    public void ErrorResponceController_Exception_Success() {
        assertEquals(errorResponse.getHttpStatus(), HttpStatus.OK);
        assertEquals(errorResponse.getMessage(), "message");
        assertEquals(errorResponse.getCause(), "cause");
        assertEquals(errorResponse2.getHttpStatus(), HttpStatus.OK);
        assertEquals(errorResponse2.getMessage(), "message");
    }

    @Test
    public void ErrorResponceSettersAndGetters_Success() {
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        assertEquals(errorResponse.getHttpStatus(), HttpStatus.BAD_REQUEST);

        errorResponse2.setHttpStatus(HttpStatus.BAD_REQUEST);
        assertEquals(errorResponse2.getHttpStatus(), HttpStatus.BAD_REQUEST);

        errorResponse.setCause("qwe");
        assertEquals(errorResponse.getCause(), "qwe");

        errorResponse2.setCause("qwe");
        assertEquals(errorResponse2.getCause(), "qwe");

        errorResponse.setMessage("qwe");
        assertEquals(errorResponse.getMessage(), "qwe");

        errorResponse2.setMessage("qwe");
        assertEquals(errorResponse2.getMessage(), "qwe");

        errorResponse.setTimestamp(LocalDateTime.now().format(formatter));
        assertEquals(errorResponse.getTimestamp(), LocalDateTime.now().format(formatter));

        errorResponse2.setTimestamp(LocalDateTime.now().format(formatter));
        assertEquals(errorResponse2.getTimestamp(), LocalDateTime.now().format(formatter));
    }
}
