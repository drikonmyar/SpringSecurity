package com.easter.SpringSecurity.config;

import com.easter.SpringSecurity.Repository.RoleRepository;
import com.easter.SpringSecurity.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final List<String> ignoredPaths = List.of("/greet/welcome", "/user", "/role", "/assign", "/assign/**");

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(ignoredPaths.toArray(String[]::new));
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails admin = User.withUsername("admin")
//                .password("{noop}admin") // {noop} indicates no password encoder
//                .authorities("ADMIN_VIEW_ONLY") // Assign authority
//                .build();
//        return new InMemoryUserDetailsManager(admin);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository, RoleRepository roleRepository){
        return username -> {
            com.easter.SpringSecurity.Entity.User user = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
            List<String> roles = roleRepository.findRolesByUserId(user.getId());

            List<GrantedAuthority> authorities = new ArrayList<>();
            for(String role : roles){
                authorities.add(new SimpleGrantedAuthority(role));
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getName(),
                    "{noop}"+user.getPassword(),
                    authorities
            );
        };
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


}
