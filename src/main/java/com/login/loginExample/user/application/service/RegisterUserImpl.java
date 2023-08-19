package com.login.loginExample.user.application.service;

import com.login.loginExample.user.domain.service.IRegisterUserService;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.RegisterRequest;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserImpl implements IRegisterUserService {
    
    @Override
    public MessageResponse registerUser(RegisterRequest registerRequest) {
        return null;
    }
}
