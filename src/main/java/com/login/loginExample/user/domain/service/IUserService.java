package com.login.loginExample.user.domain.service;


import com.login.loginExample.user.infrastructure.entrypoint.dto.request.UserRequestDto;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.UserResponseDto;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public interface IUserService {
    List<UserResponseDto> getAllUsers () throws EmptyEntityException, EmptyEntityException;
    UserResponseDto getUserById (long id) throws UserNotFoundException, UserNotFoundException;
    UserResponseDto createUser (UserRequestDto userRequestDto) throws UserExistException, SQLException, InvalidPasswordException, CredentialNullException, UserExistException;
    UserResponseDto updateUser (long id, UserRequestDto userRequestDto) throws UserNotFoundException;
    boolean deleteUser (long id) throws UserNotFoundException;



}
