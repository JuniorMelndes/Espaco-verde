package com.espacoverde.exception.type;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class EspacoException extends RuntimeException {
	
	private final HttpStatus httpStatus;
	public EspacoException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
