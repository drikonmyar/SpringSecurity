package com.easter.SpringSecurity.Dto;

import com.easter.SpringSecurity.Entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserNameRoleNameDto {
    int userId;
    String userName;
    List<Role> roles;
}
