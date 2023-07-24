package com.login.loginExample.user.infrastructure.entrypoint.utils.exception;

public class UserExistException extends Exception {
    public UserExistException(String message) {
        super(message);
    }
}
