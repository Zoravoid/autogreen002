package com.iucosoft.mylinksspringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorObject> handleBadRequestException(BadRequestException ex, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }
}
