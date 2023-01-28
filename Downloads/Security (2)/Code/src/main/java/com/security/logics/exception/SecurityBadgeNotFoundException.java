package com.security.logics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SecurityBadgeNotFoundException extends Exception {

    public SecurityBadgeNotFoundException(String message) {
        super(message);
    }
}
