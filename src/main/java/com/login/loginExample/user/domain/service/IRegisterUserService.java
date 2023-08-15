package com.login.loginExample.user.domain.service;

import com.login.loginExample.user.infrastructure.entrypoint.dto.request.RegisterRequest;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface IRegisterUserService {
    MessageResponse registerUser(RegisterRequest registerRequest);
}
