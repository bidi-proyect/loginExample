package com.login.loginExample.user.infrastructure.entrypoint.service;

import com.login.loginExample.user.application.service.OtpServiceImpl;
import com.login.loginExample.user.domain.entity.ERole;
import com.login.loginExample.user.domain.entity.Rol;
import com.login.loginExample.user.domain.entity.User;
import com.login.loginExample.user.domain.service.AuthService;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.LoginRequest;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.SignupRequest;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.JwtResponse;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.MessageResponse;
import com.login.loginExample.user.infrastructure.entrypoint.mapper.UserMapper;
import com.login.loginExample.user.infrastructure.entrypoint.repository.IUserRepository;
import com.login.loginExample.user.infrastructure.entrypoint.security.jwt.JwtUtils;
import com.login.loginExample.user.infrastructure.entrypoint.utils.ValidateRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ValidateRol validateRol;

    @Autowired
    OtpServiceImpl otpService;
    @Override
    public JwtResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public MessageResponse registerUser(SignupRequest signupRequest) {

        RestTemplate restTemplate = new RestTemplate();

        if (iUserRepository.existsByEmail(signupRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        String otp = otpService.generateCode(signupRequest.getPhoneNumber());

        User user = UserMapper.convertRequestToUser(signupRequest);
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setRoles(validateRol.validateRol(signupRequest.getRol()));
        user.setOtp(otp);
        iUserRepository.save(user);

        otpService.sendCode(signupRequest.getPhoneNumber());

        return new MessageResponse("User registered successfully!");
    }

    @Override
    public MessageResponse confirmRegistration(String phoneNumber, String otp) {
        String cachedOtp = otpService.getCacheOtp(phoneNumber);

        if (cachedOtp.equals(otp)) {
            User user = iUserRepository.findByPhoneNumber(phoneNumber);

            if (user.isConfirmed()) {
                return new MessageResponse("User is already confirmed.");
            }

            user.setConfirmed(true);
            iUserRepository.save(user);
            otpService.clearOtp(phoneNumber);
            return new MessageResponse("User registration confirmed successfully!");
        } else {
            return new MessageResponse("Invalid OTP.");
        }
    }

}
