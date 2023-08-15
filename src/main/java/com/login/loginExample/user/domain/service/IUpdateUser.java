package com.login.loginExample.user.domain.service;

import com.login.loginExample.user.infrastructure.entrypoint.dto.request.UserRequestDto;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.UserResponseDto;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface IUpdateUser {
    UserResponseDto updateUser (long id, UserRequestDto userRequestDto) throws UserNotFoundException;
}
