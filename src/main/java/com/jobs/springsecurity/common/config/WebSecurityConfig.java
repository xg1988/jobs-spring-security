package com.jobs.springsecurity.common.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;


@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private UserLoginService userLoginService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public WebSecurityCustomizer configure(){
        return (web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // csrf 비활성화 (사이트 요청 위조) ---> csrf 토큰이 없어도 서버는 응답
        // 스프링에서는 csrf 기본은 활성화 (보안 목적) ---> csrf 토큰을 url에 포함해야 서버는 응답
       /* http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers("/", "/signup").permitAll()
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .authenticationManager(authenticationManager())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        */
        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/", "/signup/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .authenticationManager(authenticationManager())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(getbCryptPasswordEncoder());
        provider.setUserDetailsService(userLoginService);
        return new ProviderManager(provider);
    }

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }
}
