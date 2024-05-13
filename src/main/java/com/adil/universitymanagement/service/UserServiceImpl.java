package com.adil.universitymanagement.service;

import com.adil.universitymanagement.config.JwtService;
import com.adil.universitymanagement.entity.User;
import com.adil.universitymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserByJwt(String jwt) {

        String username = jwtService.extractUsername(jwt);

        Optional<User> user = userRepository.findByUsername(username);

        return user;
    }

    public void updateUser(String username,String newUsername,String password){
        User user = userRepository.findByUsername(username).get();
        user.setUsername(newUsername);
        user.setPassword(password);

        userRepository.save(user);
    }
}
