package com.jobs.springsecurity.api.login;


import com.jobs.springsecurity.common.config.UserSecurityDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class LoginController {
    private LoginService loginService;

    @GetMapping(value = "/signup/{userId}/{password}/{email}/{name}/{role}")
    public UserSecurityDto signup(
            @PathVariable String userId
            , @PathVariable String password
            , @PathVariable String email
            , @PathVariable String name
            , @PathVariable String role) throws Exception {
        return new UserSecurityDto(loginService.signup(
                userId, password, email, name, role
        ));
    }
}
