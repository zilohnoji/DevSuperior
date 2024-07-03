package com.devsuperior.demo.controllers.handler;

import com.devsuperior.demo.exceptions.ConstraintError;
import com.devsuperior.demo.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ConstraintError> methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ConstraintError error = handleConstraintExceptionFactory(request.getRequestURI());
        exception.getBindingResult().getFieldErrors().forEach(field -> error.addFieldMessage(field.getField(), field.getDefaultMessage()));
        return ResponseEntity.status(ConstraintError.STATUS.value()).body(error);
    }

    private StandardError handleExceptionFactory(String error, Integer status, String path) {
        return new StandardError(error, status, path);
    }

    private ConstraintError handleConstraintExceptionFactory(String path) {
        return new ConstraintError(ConstraintError.STATUS.value(), path);
    }
}