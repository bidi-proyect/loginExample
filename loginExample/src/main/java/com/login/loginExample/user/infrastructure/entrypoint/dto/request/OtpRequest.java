package com.login.loginExample.user.infrastructure.entrypoint.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OtpRequest {
    @JsonProperty(value = "phoneNumber")
    private String phoneNumber;
    @JsonProperty(value = "otp")
    private String otp;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
