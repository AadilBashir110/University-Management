package com.adil.universitymanagement.config;

import com.adil.universitymanagement.entity.Role;
import com.adil.universitymanagement.entity.User;
import com.adil.universitymanagement.payload.response.AuthenticationResponse;
import com.adil.universitymanagement.payload.request.LoginRequest;
import com.adil.universitymanagement.payload.request.RegisterRequest;
import com.adil.universitymanagement.repository.UserRepository;
import com.adil.universitymanagement.service.StudentService;
import com.adil.universitymanagement.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public AuthenticationResponse register(RegisterRequest request) {
       var user = User.builder()
               .username(request.getUsername())
               .password(passwordEncoder.encode(request.getPassword()))
               .role(request.getRole())
               .build();
       if(request.getRole().equals(Role.ROLE_TEACHER)) {
          if(request.getTeacherBean().getEmail().equals(request.getUsername())){
              teacherService.createTeacher(request.getTeacherBean());
              userRepository.save(user);
          }
          else {
              throw new RuntimeException("Invalid teacher username");
          }
       }
       else if(request.getRole().equals(Role.ROLE_STUDENT)) {
           if(request.getStudentBean().getEmail().equals(request.getUsername())){
               studentService.createStudent(request.getStudentBean());
               userRepository.save(user);
           }
           else {
               throw new RuntimeException("Invalid teacher username");
           }
       } else if (request.getRole().equals(Role.ROLE_ADMIN)) {
           System.out.println("Admin created successfully");
           userRepository.save(user);
       }
       else {
           System.out.println("Invalid role specified");
       }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
               .token(jwtToken)
                .message("Register Success")
               .build();
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("Login Success")
                .build();
    }
}
