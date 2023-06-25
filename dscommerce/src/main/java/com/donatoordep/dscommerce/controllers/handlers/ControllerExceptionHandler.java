package com.donatoordep.dscommerce.controllers.handlers;

import com.donatoordep.dscommerce.dto.ValidationError;
import com.donatoordep.dscommerce.services.exceptions.CustomizedException;
import com.donatoordep.dscommerce.services.exceptions.DatabaseViolationReferentialException;
import com.donatoordep.dscommerce.services.exceptions.NotFoundResourceException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<CustomizedException> notFoundResource(
            NotFoundResourceException e, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomizedException(
                Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(DatabaseViolationReferentialException.class)
    public ResponseEntity<CustomizedException> databaseViolationReferential(
            DatabaseViolationReferentialException e, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomizedException(
                Instant.now(), HttpStatus.CONFLICT.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomizedException> methodArgumentNotValid(
            MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError err = new ValidationError(
                Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Invalid Data",
                request.getRequestURI());
        e.getBindingResult().getFieldErrors().forEach(x -> err.addError(x.getField(), x.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }
}
