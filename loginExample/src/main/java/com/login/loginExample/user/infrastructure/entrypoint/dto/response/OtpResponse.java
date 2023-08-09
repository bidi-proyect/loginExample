package com.login.loginExample.user.infrastructure.entrypoint.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OtpResponse {
    @JsonProperty(value = "id")
    private long id;
    @JsonProperty(value = "code")
    private String code;
    @JsonProperty(value = "createDate")
    private Date createDate;
}
