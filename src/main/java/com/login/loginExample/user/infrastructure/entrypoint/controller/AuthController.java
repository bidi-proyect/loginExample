package com.login.loginExample.user.infrastructure.entrypoint.controller;

import com.login.loginExample.user.application.service.OtpServiceImpl;
import com.login.loginExample.user.domain.service.AuthService;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.LoginRequest;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.OtpRequest;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.SignupRequest;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.JwtResponse;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("signin")
    public ResponseEntity<JwtResponse> authUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.loginUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        MessageResponse messageResponse = authService.registerUser(signupRequest);
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/confirm-registration")
    public ResponseEntity<MessageResponse> confirmRegistration(@RequestBody OtpRequest otpRequest) {
        MessageResponse response = authService.confirmRegistration(otpRequest.getPhoneNumber(),otpRequest.getOtp());
        return ResponseEntity.ok(response);
    }
}
