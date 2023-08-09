package com.login.loginExample.user.infrastructure.entrypoint.utils.exception;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String message) {
        super(message);
    }
}
