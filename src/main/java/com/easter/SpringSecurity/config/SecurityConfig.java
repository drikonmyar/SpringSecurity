package com.easter.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final List<String> ignoredPaths = List.of("/welcome");

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(ignoredPaths.toArray(String[]::new));
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin") // {noop} indicates no password encoder
                .authorities("ADMIN_VIEW_ONLY") // Assign authority
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

}
