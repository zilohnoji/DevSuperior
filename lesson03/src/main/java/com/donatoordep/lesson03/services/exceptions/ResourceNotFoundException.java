package com.donatoordep.lesson03.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    private static final String ERROR = "Error: not found resource";

    public ResourceNotFoundException() {
        super(ERROR);
    }
}
