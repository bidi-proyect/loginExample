package com.login.loginExample.user.application.service;


import com.login.loginExample.user.domain.entity.User;
import com.login.loginExample.user.domain.service.IUserService;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.UserRequestDto;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.UserResponseDto;
import com.login.loginExample.user.infrastructure.entrypoint.mapper.UserMapper;
import com.login.loginExample.user.infrastructure.entrypoint.repository.IUserRepository;
import com.login.loginExample.user.infrastructure.entrypoint.utils.ValidateCredentials;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.CredentialNullException;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.EmptyEntityException;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.UserExistException;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserImpl implements IUserService {
	Logger logger = LoggerFactory.getLogger(UserImpl.class);
    @Autowired
    IUserRepository iUserRepository;
    @Override
    public List<UserResponseDto> getAllUsers() throws EmptyEntityException {

		List<User> userList = iUserRepository.findAll();

		return userList.stream()
				.map(UserMapper::convertUserToResponseDTO)
				.collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(long id) throws UserNotFoundException {

		Optional <User> optionalUser = iUserRepository.findById(id);

		if (!optionalUser.isPresent()) {
			logger.info("Impl | User not found.");
			throw new UserNotFoundException("User not found");
		}

		User user = optionalUser.get();
		return UserMapper.convertUserToResponseDTO(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) throws CredentialNullException, UserExistException {

		ValidateCredentials.credentialValidator(userRequestDto.getName(), "Name cannot be null");
		ValidateCredentials.credentialValidator(userRequestDto.getLastname(), "Lastname cannot be null");
		ValidateCredentials.credentialValidator(userRequestDto.getPassword(), "Password cannot be null");

		Optional<User> userEmail = this.iUserRepository.findByEmail(userRequestDto.getEmail());

		if (userEmail.isPresent()) {
			logger.info("Impl | User exist.");
			throw new UserExistException("User exist.");
		}

		User user = UserMapper.convertRequestToUser(userRequestDto);
		iUserRepository.saveAndFlush(user);
		logger.info("Impl | Save&Flush");
		return UserMapper.convertUserToResponseDTO(user);
    }

    @Override
    public UserResponseDto updateUser(long id, UserRequestDto userRequestDto)
			throws UserNotFoundException {

		User user = iUserRepository.findUserById(id);

		if (user == null) {
			logger.info("Impl | User not found.");
			throw new UserNotFoundException("User not found.");
		}

		user = UserMapper.convertRequestToUser(userRequestDto);
		iUserRepository.save(user);
		return UserMapper.convertUserToResponseDTO(user);
    }

    @Override
    public boolean deleteUser(long id)
			throws UserNotFoundException {
		Optional<User> optionalUser = iUserRepository.findById(id);
		if (!optionalUser.isPresent()) {
			logger.info("Impl | User not found.");
			throw new UserNotFoundException("User not found");
		}
		User user = optionalUser.get();
		iUserRepository.delete(user);
		return true;
    }
}
