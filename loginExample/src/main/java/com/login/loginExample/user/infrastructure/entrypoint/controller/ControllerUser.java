package com.login.loginExample.user.infrastructure.entrypoint.controller;


import com.login.loginExample.user.domain.service.IUserService;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.UserRequestDto;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.UserResponseDto;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.EmptyEntityException;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.UserExistException;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("user")
@Validated
public class ControllerUser {


	Logger logger= LoggerFactory.getLogger(ControllerUser.class);
    @Autowired
    IUserService iUserService;

    @GetMapping ("all")
    @ResponseBody
    public ResponseEntity<UserResponseDto> getAllUser(@RequestHeader("Authorization") String token) throws EmptyEntityException {
        List<UserResponseDto> userResponseDto = iUserService.getAllUsers();
        if (userResponseDto == null) {
            throw new EmptyEntityException("Entity is empty");
        }
        return new ResponseEntity(userResponseDto, HttpStatus.OK);
    }

    @GetMapping ("{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable long id) throws UserNotFoundException {
        UserResponseDto userResponseDto = iUserService.getUserById(id);
        if(userResponseDto == null) {
            throw new UserNotFoundException("User not found.");
        }
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping ("/")
    @ResponseBody
    public ResponseEntity<UserResponseDto> registerUser(
            @Valid
            @RequestBody UserRequestDto userRequestDto) throws Exception {
        UserResponseDto userResponseDto = this.iUserService.createUser(userRequestDto);
         if (userResponseDto == null) {
             throw new UserExistException("User exist.");
        }
        return new ResponseEntity(userResponseDto,HttpStatus.OK);
    }

    @PutMapping ("{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable long id,
            @RequestBody UserRequestDto userRequestDto) throws UserNotFoundException {
        UserResponseDto response = iUserService.updateUser(id, userRequestDto);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @DeleteMapping ("{id}")
    @ResponseBody
    public ResponseEntity<String> deleteUser (
            @PathVariable long id) throws UserNotFoundException {
        iUserService.deleteUser(id);
        return new ResponseEntity("Deleted.", HttpStatus.OK);
    }
}
