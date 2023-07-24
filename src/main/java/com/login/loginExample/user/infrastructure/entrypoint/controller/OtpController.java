package com.login.loginExample.user.infrastructure.entrypoint.controller;

import com.login.loginExample.user.domain.service.IOtpService;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.OtpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "code")
public class OtpController {
    @Autowired
    IOtpService iOtpService;

    @GetMapping(value = "send")
    @PreAuthorize("hasRole(ROL_ADMIN)")
    public boolean sendCode(@RequestBody OtpRequest otpRequest) {

        return iOtpService.sendCode(otpRequest.getPhoneNumber());
    }
}
