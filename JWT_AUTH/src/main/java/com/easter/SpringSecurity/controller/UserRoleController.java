package com.easter.SpringSecurity.controller;

import com.easter.SpringSecurity.Dto.RoleDto;
import com.easter.SpringSecurity.Dto.UserNameRoleNameDto;
import com.easter.SpringSecurity.Dto.UserRoleDto;
import com.easter.SpringSecurity.Entity.Role;
import com.easter.SpringSecurity.Entity.UserRole;
import com.easter.SpringSecurity.Service.RoleService;
import com.easter.SpringSecurity.Service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PutMapping("/assign")
    public ResponseEntity<UserRole> assignRole(@RequestBody UserRoleDto userRoleDto) throws Exception {
        return new ResponseEntity<>(this.userRoleService.assignRole(userRoleDto), HttpStatus.CREATED);
    }

    @GetMapping("/assign/{userId}")
    public ResponseEntity<UserNameRoleNameDto> getUserRoles(@PathVariable("userId") int userId) throws Exception {
        return new ResponseEntity<>(this.userRoleService.getUserRole(userId), HttpStatus.OK);
    }

}
