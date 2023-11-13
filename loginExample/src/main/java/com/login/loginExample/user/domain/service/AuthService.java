package com.login.loginExample.user.domain.service;

import com.login.loginExample.user.infrastructure.entrypoint.dto.request.LoginRequest;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.SignupRequest;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    JwtResponse loginUser(LoginRequest loginRequest);
    MessageResponse registerUser(SignupRequest signupRequest);
    MessageResponse confirmRegistration(String phoneNumber, String otp);

}
