package com.devsuperior.dsmeta.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final String ERROR = "Error: resource not found";

    public ResourceNotFoundException(){
        super(ERROR);
    }
}
