package com.jobs.springsecurity.common.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class UserLoginService implements UserDetailsService {

    private UserDtoRepository userDtoRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto signup(
            String userId
            , String password
            , String email
            , String name
    ){
        return userDtoRepository.save(
                UserDto.builder()
                        .email(email)
                        .name(name)
                        .password(bCryptPasswordEncoder.encode(password))
                        .userId(userId)
                        .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username: {}" , username);

        UserDto userDto = userDtoRepository.findById(username).orElseThrow(NullPointerException::new);
        log.info("userDto: {}" , userDto);
        UserSecurityDto userSecurityDto = new UserSecurityDto(userDto);

        return userSecurityDto;
    }
}
