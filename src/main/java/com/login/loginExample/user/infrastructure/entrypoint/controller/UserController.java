package com.login.loginExample.user.infrastructure.entrypoint.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("hola")
    public String holamundo() {
        return "Hola mundo";
    }
}
