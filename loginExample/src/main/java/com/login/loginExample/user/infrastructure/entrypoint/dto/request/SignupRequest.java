package com.login.loginExample.user.infrastructure.entrypoint.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;


@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupRequest extends UserRequestDto{

}
