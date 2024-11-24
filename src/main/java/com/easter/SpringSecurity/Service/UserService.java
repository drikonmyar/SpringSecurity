package com.easter.SpringSecurity.Service;

import com.easter.SpringSecurity.Dto.UserDto;
import com.easter.SpringSecurity.Entity.User;
import com.easter.SpringSecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return this.userRepository.save(user);
    }

}
