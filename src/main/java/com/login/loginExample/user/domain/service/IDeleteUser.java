package com.login.loginExample.user.domain.service;


import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface IDeleteUser {
    boolean deleteUser (long id) throws UserNotFoundException;

}
