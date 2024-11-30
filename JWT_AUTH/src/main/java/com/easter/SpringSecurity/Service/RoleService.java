package com.easter.SpringSecurity.Service;

import com.easter.SpringSecurity.Dto.RoleDto;
import com.easter.SpringSecurity.Dto.UserDto;
import com.easter.SpringSecurity.Entity.Role;
import com.easter.SpringSecurity.Entity.User;
import com.easter.SpringSecurity.Repository.RoleRepository;
import com.easter.SpringSecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole (RoleDto roleDto){
        Role role = new Role();
        role.setRole(roleDto.getRole());
        return this.roleRepository.save(role);
    }

}
