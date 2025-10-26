package com.iucosoft.mylinksspringboot.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(){

    }
}

// action not allowed
// resource not found - generala
//
