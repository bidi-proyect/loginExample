package com.login.loginExample.user.infrastructure.entrypoint.mapper;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.loginExample.user.domain.entity.Otp;
import com.login.loginExample.user.infrastructure.entrypoint.dto.request.OtpRequest;
import com.login.loginExample.user.infrastructure.entrypoint.dto.response.OtpResponse;

public class OtpMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Otp convertRequestToOtp (OtpRequest otpRequest) {
        return objectMapper.convertValue(otpRequest, Otp.class);
    }

    public static OtpResponse convertOtpToResponse (Otp otp) {
        return objectMapper.convertValue(otp, OtpResponse.class);
    }
}
