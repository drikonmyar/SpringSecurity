package com.easter.SpringSecurity.Service;

import com.easter.SpringSecurity.Dto.UserDto;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserDto createUser(OAuth2User oAuth2User){
        UserDto userDto = new UserDto();
        userDto.setName(oAuth2User.getAttribute("name"));
        userDto.setEmail(oAuth2User.getAttribute("email"));
        return userDto;
    }

}
