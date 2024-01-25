package com.neGleb1.api.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(NotFoundException e) {
        ExceptionResponse response = new ExceptionResponse(
            e.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            ZonedDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(ImageNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse(
            e.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            ZonedDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<ExceptionResponse> handleException(StorageException e) {
        ExceptionResponse response = new ExceptionResponse(
            e.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            ZonedDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleException(InsufficientAuthenticationException e) {
        ExceptionResponse response = new ExceptionResponse(
            e.getMessage(),
            HttpStatus.FORBIDDEN.value(),
            ZonedDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException e) {
        ExceptionResponse response = new ExceptionResponse(
            e.getMessage(),
            HttpStatus.UNAUTHORIZED.value(),
            ZonedDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        ExceptionResponse response = new ExceptionResponse(
            e.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ZonedDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
