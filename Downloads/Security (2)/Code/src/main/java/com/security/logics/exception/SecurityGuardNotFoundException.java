package com.security.logics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SecurityGuardNotFoundException extends Exception {

    public SecurityGuardNotFoundException(String message) {
        super(message);
    }
}
