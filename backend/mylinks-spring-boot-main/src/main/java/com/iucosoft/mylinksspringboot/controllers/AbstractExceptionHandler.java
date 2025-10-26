package com.iucosoft.mylinksspringboot.controllers;

import com.iucosoft.mylinksspringboot.exceptions.MyLinksRuntimeException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractExceptionHandler {

    public static final String TIMESTAMP = "timestamp";
    public static final String STATUS = "status";
    public static final String ERROR = "error";
    public static final String ERRORS = "errors";
    public static final String PATH = "path";
    public static final String MESSAGE_BAD_REQUEST = "Bad Request";
    public static final String USER_MESSAGE = "Bad Request";

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<Map<String, Object>> handlePropertyValueException(PropertyValueException ex, WebRequest request) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(TIMESTAMP, LocalDateTime.now());
        errorResponse.put(STATUS, HttpStatus.BAD_REQUEST.value());
        errorResponse.put(ERROR, MESSAGE_BAD_REQUEST);
        errorResponse.put(PATH, request.getDescription(false).replace("uri=", ""));
        Map<String, Object> validationErrors = new HashMap<>();
        if (ex.getPropertyName() != null) {
            validationErrors.put(ex.getPropertyName(), null);
        }
        errorResponse.put(ERRORS, validationErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MyLinksRuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleMyLinksRuntimeException(MyLinksRuntimeException ex, WebRequest request) {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put(TIMESTAMP, LocalDateTime.now());
        errorResponse.put(STATUS, ex.getErrorStatus().value());
        errorResponse.put(USER_MESSAGE, ex.getUserMessage());
        errorResponse.put(PATH, request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(ex.getErrorStatus()).body(errorResponse);
    }

}