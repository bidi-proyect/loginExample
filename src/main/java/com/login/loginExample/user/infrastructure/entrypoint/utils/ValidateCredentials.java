package com.login.loginExample.user.infrastructure.entrypoint.utils;


import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.CredentialNullException;

public class ValidateCredentials {
    public static void credentialValidator(String credential, String message) throws CredentialNullException {
        if (credential == null) {
            throw new CredentialNullException(message);
        }
    }

}
