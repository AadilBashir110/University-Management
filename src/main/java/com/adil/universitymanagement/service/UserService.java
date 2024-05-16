package com.adil.universitymanagement.service;

import com.adil.universitymanagement.entity.Role;


public interface UserService {
    String getUsernameFromToken();
    void updateUser(String username,String newUsername,String password);
    public Role getRoleFromUsername();
}
