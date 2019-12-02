package com.example.postapi.exception;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertNotNull;

public class ExceptionHandlerTest {
    ExceptionHandler exceptionHandler;
    String message = "msg";
    @Test
    public void handleException_ExceptionHandler_Success() {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, message);
        assertNotNull(errorResponse);
    }

//    @org.springframework.web.bind.annotation.ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleException(Exception e) {
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(Exception e) {
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
//                e.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidInputException.class)
//    public ResponseEntity<ErrorResponse> handleInvalidInputException(Exception e) {
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,
//                "Invalid input",
//                e.getMessage());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
//                                                                  HttpHeaders headers, HttpStatus status,
//                                                                  WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
}
