package com.espacoverde.feature.exception;

import org.springframework.http.HttpStatus;

import com.espacoverde.exception.type.EspacoException;

public class InvalidAttributeException extends EspacoException {
	
	public InvalidAttributeException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
