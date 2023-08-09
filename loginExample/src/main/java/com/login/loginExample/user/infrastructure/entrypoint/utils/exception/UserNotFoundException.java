package com.login.loginExample.user.infrastructure.entrypoint.utils.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
