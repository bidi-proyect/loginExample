package com.login.loginExample.user.infrastructure.entrypoint.controller.exception;

import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.EmptyEntityException;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.UserExistException;
import com.login.loginExample.user.infrastructure.entrypoint.utils.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> handlerUserNotFoundException (UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmptyEntityException.class)
    public ResponseEntity<String> handlerEmptyEntityException (EmptyEntityException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(value = UserExistException.class)
    public  ResponseEntity<String> handlerUserExistException (UserExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<String> handlerSqlIntegrityException (SQLException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationException(MethodArgumentNotValidException ex, BindingResult bindingResult) {
        List<String> errorMessages = bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());


        return new ResponseEntity<>(errorMessages, HttpStatus.UNPROCESSABLE_ENTITY) ;
    }

}
