package com.login.loginExample.user.domain.service;

import com.login.loginExample.user.infrastructure.entrypoint.dto.request.OtpRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOtpService {
    String generateCode(String phoneNumber);
    String concatNumber(List<Integer> numbers);
    boolean sendCode(String phoneNumber);
}
