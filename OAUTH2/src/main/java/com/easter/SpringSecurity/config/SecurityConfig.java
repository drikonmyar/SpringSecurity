package com.easter.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final List<String> ignoredPaths = List.of("/", "/login");

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(ignoredPaths.toArray(String[]::new));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                authorize requests
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
//                configure oauth2 login
                .oauth2Login(oauth2login ->
                        oauth2login
                                .loginPage("/login")
                                .defaultSuccessUrl("/home", true)
                );
        return http.build();
    }

}
