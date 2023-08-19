package com.login.loginExample.user.domain.service;

import com.login.loginExample.user.infrastructure.entrypoint.dto.response.UserResponseDto;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.EmptyEntityException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IGetAllUsers {
    List<UserResponseDto> getAllUsers () throws EmptyEntityException;
}
