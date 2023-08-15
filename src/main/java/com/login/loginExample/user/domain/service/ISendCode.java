package com.login.loginExample.user.domain.service;

import org.springframework.stereotype.Service;

@Service
public interface ISendCode {
    boolean sendCode(String phoneNumber);
}
