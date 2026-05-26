package com.uca.pncsegundoparcialgestiontareas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ApiError.builder()
                .message(ex.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDate.now())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiError> handleBusinessRuleException(BusinessRuleException ex) {
        return new ResponseEntity<>(ApiError.builder()
                .message(ex.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDate.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(ApiError.builder()
                .message("Validacion fallida")
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDate.now())
                .errors(errors)
                .build(), HttpStatus.BAD_REQUEST);
    }

}
