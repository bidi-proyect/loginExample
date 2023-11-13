package com.login.loginExample.user.infrastructure.entrypoint.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Component
public class LoginRequest {

    @NotNull
    @JsonProperty("email")
    @Email(regexp = "^[^@]+@[^@]+\\.[^@]+$", message = "Invalid Email, ej.(email@email.com)")
    private String email;


    @NotNull
    @JsonProperty("password")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "La contraseña no es valida, ej.(Ejpl1234*)")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
