package com.easter.SpringSecurity.controller;

import com.easter.SpringSecurity.Dto.RoleDto;
import com.easter.SpringSecurity.Dto.UserDto;
import com.easter.SpringSecurity.Entity.Role;
import com.easter.SpringSecurity.Entity.User;
import com.easter.SpringSecurity.Service.RoleService;
import com.easter.SpringSecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public ResponseEntity<Role> createRole(@RequestBody RoleDto roleDto){
        return new ResponseEntity<>(this.roleService.createRole(roleDto), HttpStatus.CREATED);
    }

}
