package com.easter.SpringSecurity.controller;

import com.easter.SpringSecurity.Dto.UserDto;
import com.easter.SpringSecurity.Entity.User;
import com.easter.SpringSecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(this.userService.createUser(userDto), HttpStatus.CREATED);
    }

}
