package com.devsuperior.demo.exceptions;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ConstraintError extends StandardError {

    public static final HttpStatus STATUS = HttpStatus.UNPROCESSABLE_ENTITY;
    public static final String ERROR = "Contraint Error";
    private List<FieldMessage> errors = new ArrayList<>();

    public ConstraintError(Integer status, String path) {
        super(ERROR, status, path);
    }

    public void addFieldMessage(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }
}