package com.devsuperior.demo.exceptions;

import java.io.Serial;

public class ONBEntityNotFoundException extends RuntimeException {

    private static final String ERROR = "entity not found";

    @Serial
    private static final long serialVersionUID = -8128093686164709254L;

    public ONBEntityNotFoundException() {
        super(ERROR);
    }
}