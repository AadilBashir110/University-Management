package com.adil.universitymanagement.payload.request;


import com.adil.universitymanagement.bean.StudentBean;
import com.adil.universitymanagement.bean.TeacherBean;
import com.adil.universitymanagement.entity.Role;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterRequest {

    private String username;
    private String password;
    private Role role;

    private TeacherBean teacherBean;
}
