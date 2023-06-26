package com.donatoordep.dscommerce.services.exceptions;

public class NotFoundResourceException extends RuntimeException {

    private static final String ERROR = "error: resource not found";

    public NotFoundResourceException(){
        super(ERROR);
    }
}
