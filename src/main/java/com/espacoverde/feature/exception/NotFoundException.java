package com.espacoverde.feature.exception;

import org.springframework.http.HttpStatus;

import com.espacoverde.exception.type.EspacoException;

public class NotFoundException extends EspacoException {
	
	public NotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
