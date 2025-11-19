package com.iucosoft.mylinksspringboot.exceptions;

import org.springframework.http.HttpStatus;

public class MyLinksRuntimeException extends RuntimeException {

    private String userMessage; // mesajul pentru front-end
    private HttpStatus errorStatus;

    public MyLinksRuntimeException(String message) {
        super(message);
    }

    public MyLinksRuntimeException(String message, HttpStatus errorStatus) {
        super(message);
        this.errorStatus = errorStatus;
    }

    public MyLinksRuntimeException(String message, String userMessage) {
        super(message);
        this.userMessage = userMessage;
        this.errorStatus = HttpStatus.FORBIDDEN;
    }

    public MyLinksRuntimeException(String message, String userMessage, HttpStatus errorStatus) {
        super(message);
        this.userMessage = userMessage;
        this.errorStatus = errorStatus;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(HttpStatus errorStatus) {
        this.errorStatus = errorStatus;
    }
}
