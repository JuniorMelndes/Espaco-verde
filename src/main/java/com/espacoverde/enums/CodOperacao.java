package com.espacoverde.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CodOperacao {
    SUCCESS("Sucess"),
    ERROR("Error");


    public final String value;
}
