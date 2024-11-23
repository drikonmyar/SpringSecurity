package com.easter.SpringSecurity.Service;

import com.easter.SpringSecurity.Dto.UserDto;
import com.easter.SpringSecurity.Entity.User;
import com.easter.SpringSecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        return this.userRepository.save(user);
    }

}
