package com.dl.gm.authentication.service.exception;

public class AuthenticationServiceException extends RuntimeException {

    public AuthenticationServiceException(String message) {
        super(message);
    }

    public AuthenticationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
