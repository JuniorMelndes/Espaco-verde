package com.espacoverde.feature.exception;

import org.springframework.http.HttpStatus;

import com.espacoverde.exception.type.EspacoException;

public class AlreadyExistsException extends EspacoException {
	
	public AlreadyExistsException(String message) {
		super(message, HttpStatus.CONFLICT);
	}
}
