package com.adil.universitymanagement.service;

import com.adil.universitymanagement.config.JwtAuthenticationFilter;
import com.adil.universitymanagement.entity.Role;
import com.adil.universitymanagement.entity.User;
import com.adil.universitymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final JwtAuthenticationFilter authenticationFilter;
    private final UserRepository userRepository;

    @Override
    public String getUsernameFromToken() {
        return authenticationFilter.getEmailFromToken();
    }

    public void updateUser(String username, String newUsername, String password){
        User user = userRepository.findByUsername(username).get();
        user.setUsername(newUsername);
        user.setPassword(password);

        userRepository.save(user);
    }

    @Override
    public Role getRoleFromUsername() {
        String username = getUsernameFromToken();
        User user = userRepository.findByUsername(username).orElse(null);
        Role role = user.getRole();

        return role;
    }
}
