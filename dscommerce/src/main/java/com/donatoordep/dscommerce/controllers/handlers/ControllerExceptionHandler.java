package com.donatoordep.dscommerce.controllers.handlers;

import com.donatoordep.dscommerce.services.exceptions.CustomizedException;
import com.donatoordep.dscommerce.services.exceptions.DatabaseViolationReferentialException;
import com.donatoordep.dscommerce.services.exceptions.NotFoundResourceException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
