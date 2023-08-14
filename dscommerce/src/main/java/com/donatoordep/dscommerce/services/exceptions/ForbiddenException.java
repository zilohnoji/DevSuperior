package com.donatoordep.dscommerce.services.exceptions;

public class ForbiddenException extends RuntimeException {

    private static final String ERROR = "error: access denied";

    public ForbiddenException() {
        super(ERROR);
    }
}
