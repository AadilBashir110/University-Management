package com.adil.universitymanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(Authorize -> Authorize
                        .requestMatchers(HttpMethod.GET, "/courses", "/courses/{id}").hasAnyRole("TEACHER", "STUDENT")
                        .requestMatchers(HttpMethod.POST, "/courses/enroll-student").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/students", "/students/{id}").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.POST, "/students").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/students/update-student").hasRole("TEACHER")
                        .requestMatchers("/teacher/**").hasRole("TEACHER")
                        .anyRequest().authenticated());
               // .addFilterBefore(new jwtValidator(), BasicAuthenticationFilter.class)
              //  .csrf(csrf-> csrf.disable() )
             //   .httpBasic(); // Use Basic Authentication

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
