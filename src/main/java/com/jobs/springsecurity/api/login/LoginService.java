package com.jobs.springsecurity.api.login;

import com.jobs.springsecurity.common.config.UserDto;
import com.jobs.springsecurity.common.config.UserDtoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private UserDtoRepository userDtoRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto signup(
            String userId
            , String password
            , String email
            , String name
            , String role
    ){
        return userDtoRepository.save(
                UserDto.builder()
                        .email(email)
                        .name(name)
                        .password(bCryptPasswordEncoder.encode(password))
                        .userId(userId)
                        .role(role)
                        .build());
    }
}
