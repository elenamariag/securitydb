package com.security.logics.exception;

import lombok.Data;

@Data
public class ExceptionResponse {

    private String message;

    public ExceptionResponse(String message) {
        super();
        this.message = message;
    }

}
