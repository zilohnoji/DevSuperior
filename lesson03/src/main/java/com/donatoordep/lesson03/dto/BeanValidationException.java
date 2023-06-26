package com.donatoordep.lesson03.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class BeanValidationException extends CustomizedException {

    List<FieldMessage> errors = new ArrayList<>();

    public BeanValidationException(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }
}
