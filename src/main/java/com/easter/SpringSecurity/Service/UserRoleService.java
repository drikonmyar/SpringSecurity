package com.easter.SpringSecurity.Service;

import com.easter.SpringSecurity.Dto.RoleDto;
import com.easter.SpringSecurity.Dto.UserNameRoleNameDto;
import com.easter.SpringSecurity.Dto.UserRoleDto;
import com.easter.SpringSecurity.Entity.Role;
import com.easter.SpringSecurity.Entity.User;
import com.easter.SpringSecurity.Entity.UserRole;
import com.easter.SpringSecurity.Repository.RoleRepository;
import com.easter.SpringSecurity.Repository.UserRepository;
import com.easter.SpringSecurity.Repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserRole assignRole (UserRoleDto userRoleDto) throws Exception {
        Integer userId = userRoleDto.getUserId();
        Optional<User> user = userRepository.findById(userId);
        if(ObjectUtils.isEmpty(user)){
            throw new Exception("User not found");
        }
        String roleName = userRoleDto.getRoleName();
        Optional<Role> role = roleRepository.getRoleByName(roleName);
        if(ObjectUtils.isEmpty(role)){
            throw new Exception("Role not found");
        }
        Optional<UserRole> userRole = userRoleRepository.getUserRole(user.get().getId(),role.get().getId());
        if(ObjectUtils.isEmpty(userRole)){
            UserRole newUserRole = new UserRole();
            newUserRole.setUserId(userRoleDto.getUserId());
            newUserRole.setRoleId(role.get().getId());
            return userRoleRepository.save(newUserRole);
        }
        throw new Exception("User: (id: " + userRoleDto.getUserId() + " name: " + user.get().getName() + ") is already assigned with role: " + userRoleDto.getRoleName());
    }

    public UserNameRoleNameDto getUserRole(int userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(ObjectUtils.isEmpty(user)){
            throw new Exception("User not found");
        }
        List<Integer> roleIds = userRoleRepository.getUserRoleByUserId(userId);
        UserNameRoleNameDto userNameRoleNameDto = new UserNameRoleNameDto();
        userNameRoleNameDto.setUserId(userId);
        userNameRoleNameDto.setUserName(user.get().getName());
        List<Role> roles = new ArrayList<>();
        for(Integer roleId : roleIds){
            roles.add(this.roleRepository.findById(roleId).get());
        }
        userNameRoleNameDto.setRoles(roles);
        return userNameRoleNameDto;
    }

}
