package com.espacoverde.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.espacoverde.enums.CodOperacao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ErrorResponse {
    private final String codOperacao;
    private final String msgOperacao;


    private ErrorResponse(String msgOperacao) {
        this.codOperacao = CodOperacao.ERROR.value;
        this.msgOperacao = msgOperacao;
    }


    public static ResponseEntity<ErrorResponse> build(String message, HttpStatus status) {
        log.error(message);

        var error = new ErrorResponse(message);
        return new ResponseEntity<>(error, status);
    }

}
