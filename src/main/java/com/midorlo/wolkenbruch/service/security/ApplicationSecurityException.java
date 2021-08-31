package com.midorlo.wolkenbruch.service.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApplicationSecurityException extends RuntimeException {
    public ApplicationSecurityException() {
        super();
    }

    public ApplicationSecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationSecurityException(String message) {
        super(message);
    }

    public ApplicationSecurityException(Throwable cause) {
        super(cause);
    }
}