/*
package com.enrollmentpayrollsystem.enrollmentpayrollsystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class UserDetailsConfig {

    private final AppCredentials credentials;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails user = User.builder()
                .username(credentials.getUser().getUsername())
                .password(passwordEncoder.encode(credentials.getUser().getPassword()))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username(credentials.getAdmin().getUsername())
                .password(passwordEncoder.encode(credentials.getAdmin().getPassword()))
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}

 */
