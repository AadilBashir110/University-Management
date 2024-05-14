package com.adil.universitymanagement.service;

import com.adil.universitymanagement.entity.User;

import java.util.Optional;

public interface UserService {
    String getUsernameFromToken();

    void updateUser(String username,String newUsername,String password);
}
