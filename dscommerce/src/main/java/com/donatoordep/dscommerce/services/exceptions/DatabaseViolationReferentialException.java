package com.donatoordep.dscommerce.services.exceptions;

public class DatabaseViolationReferentialException extends RuntimeException {

    private static final String ERROR = "error: referential integrity failure";

    public DatabaseViolationReferentialException() {
        super(ERROR);
    }
}
