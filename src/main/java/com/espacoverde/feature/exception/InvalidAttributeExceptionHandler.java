package com.espacoverde.feature.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.espacoverde.exception.ErrorResponse;

@ControllerAdvice
public class InvalidAttributeExceptionHandler {

	@ExceptionHandler(InvalidAttributeException.class)
    protected ResponseEntity<ErrorResponse> handle(InvalidAttributeException exception) {
        return ErrorResponse.build(exception.getMessage(), exception.getHttpStatus());
    }
}
