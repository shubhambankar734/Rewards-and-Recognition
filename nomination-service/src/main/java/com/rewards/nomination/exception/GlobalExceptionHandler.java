package com.rewards.nomination.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleExceptions(CustomException ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setDateTime(LocalDateTime.now());
        exceptionResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
