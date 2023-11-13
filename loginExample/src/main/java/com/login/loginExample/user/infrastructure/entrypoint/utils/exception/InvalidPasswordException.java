package com.login.loginExample.user.infrastructure.entrypoint.utils.exception;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
