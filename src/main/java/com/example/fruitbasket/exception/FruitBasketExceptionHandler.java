package com.example.fruitbasket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FruitBasketExceptionHandler {
    @ExceptionHandler(FruitTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFruitNotFoundException(FruitTypeNotFoundException e) {
        ErrorResponse error = new ErrorResponse();
        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
        ErrorResponse error = new ErrorResponse();
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
