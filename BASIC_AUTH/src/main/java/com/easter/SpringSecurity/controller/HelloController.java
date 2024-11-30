package com.easter.SpringSecurity.controller;

import com.easter.SpringSecurity.config.PreAuthorizeAuthority;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class HelloController {

    @GetMapping("/welcome")
    public String greet(){
        return "Welcome ALL";
    }

    @PreAuthorize(PreAuthorizeAuthority.READ_ADMIN)
    @GetMapping("/admin")
    public String greetAdmin(){
        return "Hello Admin";
    }

    @PreAuthorize(PreAuthorizeAuthority.READ_USER)
    @GetMapping("/user")
    public String greetUser(){
        return "Hello User";
    }

}
