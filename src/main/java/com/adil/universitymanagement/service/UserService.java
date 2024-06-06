package com.adil.universitymanagement.service;

import com.adil.universitymanagement.entity.Role;
import com.adil.universitymanagement.entity.Student;


public interface UserService {
    String getUsernameFromToken();
    void updateUser(String username,String newUsername,String password);
    public Role getRoleFromUsername();
    public Long getStudentIdFromToken();
}
