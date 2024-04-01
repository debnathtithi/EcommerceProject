package com.nagarro.ecommerce.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VlidationExceptionHandler {
	
	  @ExceptionHandler(BindException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseEntity<Map<String, String>> handleBindException(BindException ex) {
	        Map<String, String> errors = new HashMap<>();
	        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	            errors.put(error.getField(), error.getDefaultMessage());
	        }
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
}
