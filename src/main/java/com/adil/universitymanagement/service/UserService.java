package com.adil.universitymanagement.service;

import com.adil.universitymanagement.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByJwt(String jwt);

    void updateUser(String username,String newUsername,String password);
}
